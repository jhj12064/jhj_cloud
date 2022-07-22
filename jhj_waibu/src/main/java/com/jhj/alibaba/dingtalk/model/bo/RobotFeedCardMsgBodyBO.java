package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

import java.util.List;

/**
 * 机器人发送-FeedCard类型消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotFeedCardMsgBodyBO {

    private List<RobotFeedCardLink> links;

    @Data
    public static class RobotFeedCardLink {
        /**
         * 单条信息文本（必填）
         */
        private String title;

        /**
         * 点击单条信息到跳转链接（必填）
         */
        private String messageURL;

        /**
         * 单条信息后面图片的URL（必填）
         * example:https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png
         */
        private String picURL;

    }
}
