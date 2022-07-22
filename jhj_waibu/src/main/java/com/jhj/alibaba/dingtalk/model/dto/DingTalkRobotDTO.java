package com.jhj.alibaba.dingtalk.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DingTalkRobotDTO implements Serializable {

    /**
     * 加密的会话ID。
     */
    private String conversationId;

    /**
     * 被@人的信息。
     */
    private List<AtUser> atUsers;

    /**
     * 加密的机器人所在的企业corpId
     */
    private String chatbotCorpId;

    /**
     * 加密的机器人ID。
     */
    private String chatbotUserId;

    /**
     * 加密的消息ID。
     */
    private String msgId;

    /**
     * 发送者昵称。
     */
    private String senderNick;

    /**
     * 是否为管理员。
     */
    private Boolean isAdmin;

    /**
     * 加密的发送者ID。
     * 使用senderStaffId，作为发送者userid值。
     */
    private String senderStaffId;

    /**
     * 当前会话的Webhook地址过期时间。
     */
    private Long sessionWebhookExpiredTime;

    /**
     * 消息的时间戳，单位ms。
     */
    private String createAt;

    /**
     * 企业内部群有的发送者当前群的企业corpId。
     */
    private String senderCorpId;

    /**
     * 1：单聊
     * 2：群聊
     */
    private String conversationType;

    /**
     * 加密的发送者ID。
     * 使用senderStaffId，作为发送者userid值。
     */
    private String senderId;

    /**
     * 群聊时才有的会话标题
     */
    private String conversationTitle;

    /**
     * 是否在@列表中
     */
    private Boolean isInAtList;

    /**
     * 当前会话的Webhook地址
     */
    private String sessionWebhook;

    /**
     * 消息文本
     */
    private Text text;

    /**
     * 目前只支持text。
     */
    private String msgtype;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text {

        /**
         * 消息文本。
         */
        private String content;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AtUser {

        /**
         * 加密的发送者ID
         */
        private String dingtalkId;

        /**
         * 当前企业内部群中员工userid值
         */
        private String staffId;
    }


}
