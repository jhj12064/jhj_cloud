package com.jhj.alibaba.dingtalk.enums;

/**
 * 单聊消息类型
 *
 */

public enum RobotSampleMsgType {
    sampleText("sampleText", "robotTextService","文本消息"),
    officialTextMsg("officialTextMsg", "robotTextService","文本消息"),

    sampleMarkdown("sampleMarkdown", "robotMarkdownService","markdown消息"),
    officialMarkdownMsg("officialMarkdownMsg", "robotMarkdownService","markdown消息"),

    sampleLink("sampleLink", "robotLinkService","链接消息"),

    officialImageMsg("officialImageMsg", "robotImageService","图片消息"),

    officialActionCardMsg("officialActionCardMsg","robotActionCardService", "卡片消息"),
    officialActionCardMsg1("officialActionCardMsg1","robotActionCardService", "卡片消息 Button横向排列"),
    officialActionCardMsg2("officialActionCardMsg2","robotActionCardService", "卡片消息 Button竖向排列"),
    sampleActionCard("sampleActionCard","robotActionCardService", "卡片消息 1个按钮"),
    sampleActionCard2("sampleActionCard2","robotActionCardService", "卡片消息 2个按钮"),
    sampleActionCard3("sampleActionCard3","robotActionCardService", "卡片消息 3个按钮"),
    ;

    private String code;

    private String serviceName;

    private String describe;

    RobotSampleMsgType(String code, String serviceName, String describe) {
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
