package com.jhj.alibaba.dingtalk.model.dto;

import com.aliyun.tea.TeaPair;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DingTalkRobotCardDTO implements Serializable {

    /**
     * 互动卡片的消息模板ID。创建消息模板后可获取模板ID
     */
    private String cardTemplateId;

    /**
     * 群ID
     */
    private String openConversationId;

    /**
     * 接收人userId列表。
     */
    private List<String> receiverUserIdList;

    /**
     * 唯一标示卡片的外部编码
     */
    private String outTrackId;

    /**
     * 用于发送卡片的机器人编码，与场景群模板中的机器人编码保持一致。
     */
    private String robotCode;

    /**
     * 发送的会话类型：
     *
     * 0：单聊
     * 1：群聊
     */
    private Integer conversationType;

    /**
     * 可控制卡片回调时的路由Key，用于指定特定的callbackUrl。
     */
    private String callbackRouteKey;

    /**
     * 卡片数据
     */
    private CardData cardData;

    /**
     * 指定用户可见的按钮列表。
     */
    private Map<String, CardData> privateData;

    /**
     * 企业机器人ID
     * 说明 robotCode 或 chatBotId 二选一必填。
     */
    private String chatBotId;

    /**
     * 用户ID类型：
     *
     * 1（默认）：userid模式
     * 2：unionId模式
     */
    private Integer userIdType;

    /**
     * 消息@人。格式：{"key":"value"}。
     *
     * key：用户ID，根据userIdType设置。
     * value：用户名。
     */
    private Map<String, String> atOpenIds;

    /**
     * 卡片操作
     */
    //private CardOptions cardOptions;

    /**
     * 是否支持转发：
     *
     * true：支持
     *
     * false：不支持
     *
     */
    private Boolean supportForward;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardData {

        /**
         * 卡片模板内容替换参数，普通文本类型。
         */
        private List<TeaPair> cardParamMap;

        /**
         * 卡片模板内容替换参数，多媒体类型
         */
        private List<TeaPair> cardMediaIdParamMap;
    }

    /*@Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardOptions {

        *//**
         * 是否支持转发：
         *
         * true：支持
         *
         * false：不支持
         *
         *//*
        private Boolean supportForward;


    }*/
}
