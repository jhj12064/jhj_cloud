package com.jhj.alibaba.dingtalk;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMediaUploadRequest;
import com.dingtalk.api.response.OapiMediaUploadResponse;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.jhj.alibaba.dingtalk.enums.UploadFileType;
import com.taobao.api.FileItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.CLIENT_URL_UPLOAD_MEDIA;

/**
 * 钉钉上传文件
 *
 */
@Slf4j
@Component
public class DingTalkUploadInterface {


    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;

    public String uploadMedia(UploadFileType t, FileItem item) throws Exception {
        try {
            String accessToken = dingTalkTokenInterface.getAccessToken();
            DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_UPLOAD_MEDIA);
            OapiMediaUploadRequest req = new OapiMediaUploadRequest();
            req.setType(t.getCode());
            // 要上传的媒体文件
            req.setMedia(item);
            OapiMediaUploadResponse rsp = client.execute(req, accessToken);
            log.info("{},rsp:{}", "uploadMedia()", JSON.toJSONString(rsp));
            if (rsp == null || !DingTalkConstant.SUCCESS_CODE.equals(rsp.getErrorCode())) {
                log.error("uploadMedia_error");
                throw new Exception("sendWorkNotice_error");
            }
            return rsp.getMediaId();
        } catch (Exception e) {
            log.error("uploadMedia_error", e);
            throw new Exception("uploadMedia_error");
        }
    }


}
