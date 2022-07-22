package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

import java.util.List;

/**
 * 机器人发送-卡片消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotActionCardMsgBodyBO {

    /**
     * 首屏会话透出的展示内容（必填）
     */
    private String title;

    /**
     * markdown格式的消息（必填）
     */
    private String text;

    /**
     * 单个按钮的标题(整体跳转使用)
     */
    private String singleTitle;

    /**
     * 点击消息跳转的URL(整体跳转使用)
     */
    private String singleURL;

    /**
     * 0：按钮竖直排列
     * 1：按钮横向排列
     */
    private String btnOrientation;

    /**
     * 按钮L(独立跳转使用)
     */
    private List<RobotActionCardBtn> btns;

    @Data
    public static class RobotActionCardBtn {

        /**
         * 按钮标题
         */
        private String title;

        /**
         * 点击按钮触发的UR
         */
        private String actionURL;
    }
}
