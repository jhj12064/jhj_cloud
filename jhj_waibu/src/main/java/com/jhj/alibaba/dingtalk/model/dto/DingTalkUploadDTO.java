package com.jhj.alibaba.dingtalk.model.dto;

import com.jhj.alibaba.dingtalk.enums.UploadFileType;
import lombok.Data;

/**
 *
 */
@Data
public class DingTalkUploadDTO {

    /**
     * 文件上传类型
     */
    private UploadFileType type;

    /**
     * 文件地址
     */
    private String filePath;
}
