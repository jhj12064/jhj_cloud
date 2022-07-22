package com.jhj.alibaba.dingtalk.model.dto;

import com.jhj.alibaba.dingtalk.enums.RobotGroupMsgType;
import com.jhj.alibaba.dingtalk.model.bo.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 钉钉机器人消息DTO
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DingTalkRobotGroupMsgDTO implements Serializable {

    /**
     * 消息类型
     */
    private RobotGroupMsgType msgtype;

    /**
     * @对象 text 和 markdown 有
     */
    private At at;

    private RobotTextMsgBodyBO text;

    private RobotLinkMsgBodyBO link;

    private RobotMarkdownMsgBodyBO markdown;

    private RobotActionCardMsgBodyBO actionCard;

    private RobotFeedCardMsgBodyBO feedCard;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class At {

        /**
         * 被@人的手机号。
         */
        private List<String> atMobiles = Collections.EMPTY_LIST;

        /**
         * 被@人的用户userid
         * 测试发现 userid 不会被 @
         */
        private List<String> atUserIds = Collections.EMPTY_LIST;

        /**
         * 是否@所有人
         */
        private Boolean isAtAll = true;
    }
}
