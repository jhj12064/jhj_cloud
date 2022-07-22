package com.jhj.alibaba.dingtalk.enums;

/**
 * 群聊消息类型
 *
 */

public enum RobotGroupMsgType {
    text("text", "robotTextService","文本消息"),
    link("link", "robotLinkService","链接消息"),
    markdown("markdown", "robotMarkdownService","markdown消息"),
    actionCard("actionCard","robotActionCardService", "卡片消息"),
    feedCard("feedCard", "robotFeedCardService", "feedCard"),
    ;

    private String code;

    private String serviceName;

    private String describe;

    RobotGroupMsgType(String code, String serviceName, String describe) {
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
