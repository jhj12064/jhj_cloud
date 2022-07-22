package com.jhj.alibaba.dingtalk.enums;

public enum WorkNoticeType {
    TEXT("text", "workNoticeTextService", "文本消息"),
    VOICE("voice", "workNoticeVoiceService", "语音消息"),
    IMAGE("image", "workNoticeImageService", "图片消息"),
    FILE("file", "workNoticeFileService", "文件消息"),
    LINK("link", "workNoticeLinkService", "链接消息"),
    MARKDOWN("markdown", "workNoticeMarkdownService", "markdown消息"),
    OA("oa", "workNoticeOaService", "OA消息"),
    ACTION_CARD("action_card", "workNoticeActionCardService", "卡片消息"),
    ;

    private String code;

    private String serviceName;

    private String describe;

    WorkNoticeType(String code, String serviceName, String describe) {
        this.code = code;
        this.serviceName = serviceName;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
