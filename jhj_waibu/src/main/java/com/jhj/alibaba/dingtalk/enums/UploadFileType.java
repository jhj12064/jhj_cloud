package com.jhj.alibaba.dingtalk.enums;

/**
 * 文件上传类型
 *
 */

public enum UploadFileType {
    TEXT("video", "视频"),
    VOICE("voice", "语音"),
    IMAGE("image", "图片"),
    FILE("file", "文件")
    ;

    private String code;

    private String describe;

    UploadFileType(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
