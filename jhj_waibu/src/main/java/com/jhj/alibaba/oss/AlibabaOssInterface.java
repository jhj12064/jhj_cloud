package com.jhj.alibaba.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.aliyuncs.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 */
@Slf4j
@Component
public class AlibabaOssInterface {

    @Value("${aliyun.accessKey}")
    String accessKey;

    @Value("${aliyun.accessSecret}")
    String accessSecret;

    @Value("${aliyun.oss.endpoint}")
    String endpoint;

    @Value("${aliyun.oss.bucket-name}")
    String bucketName;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public String upload(MultipartFile inputFile) throws IOException {
        log.info("正在执行文件上传，filename:{}", inputFile.getOriginalFilename());
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        // 创建InitiateMultipartUploadRequest对象。
        String suffix = Objects.requireNonNull(inputFile.getOriginalFilename()).substring(inputFile.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = sdf.format(new Date()) + "/" + UUID.randomUUID().toString() + "." + suffix;
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, fileName);

        // 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // request.setObjectMetadata(metadata);

        // 初始化分片。
        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = upresult.getUploadId();

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List <PartETag> partETags = new ArrayList <>();
        // 计算文件有多少个分片。
        final long partSize = 1024 * 1024L;   // 1MB
        long fileLength = inputFile.getSize();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        // 遍历分片上传。
        for (int i = 0; i < partCount; i++) {
            InputStream inputStream = inputFile.getInputStream();
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            // 跳过已经上传的分片。
            inputStream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(fileName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(inputStream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
            partETags.add(uploadPartResult.getPartETag());
        }

        // 创建CompleteMultipartUploadRequest对象。
        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, fileName, uploadId, partETags);

        // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
        // completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);

        // 完成上传。
        ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
        return fileName;
    }

    public void download(String fileUrl, String fileName, String contentType, boolean preview, HttpServletResponse response) {
        if (!StringUtils.hasText(fileUrl)) {
            return;
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        InputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            String suffix = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
            if ("jpg".equals(suffix) || "png".equals(suffix) || "jpeg".equals(suffix)) {
                String style = "image/quality,q_80";
                GetObjectRequest request = new GetObjectRequest(bucketName, fileUrl);
                request.setProcess(style);
                OSSObject ossObject = ossClient.getObject(request);
                inputStream = ossObject.getObjectContent();
            } else {
                OSSObject ossObject = ossClient.getObject(bucketName, fileUrl);
                inputStream = ossObject.getObjectContent();
            }
            //缓冲文件输出流
            outputStream = new BufferedOutputStream(response.getOutputStream());
            // 为防止文件名出现乱码
            response.setContentType(contentType);
            if (preview) {
                //通知浏览器在线打开
                response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "utf-8"));
            } else {
                //通知浏览器以附件形式下载
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            }
            byte[] car = new byte[1024];
            int L;
            while ((L = inputStream.read(car)) != -1) {
                outputStream.write(car, 0, L);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            ossClient.shutdown();
        }
    }

    public InputStream download(String fileUrl) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
        OSSObject ossObject = ossClient.getObject(bucketName, fileUrl);
        return ossObject.getObjectContent();
    }

}
