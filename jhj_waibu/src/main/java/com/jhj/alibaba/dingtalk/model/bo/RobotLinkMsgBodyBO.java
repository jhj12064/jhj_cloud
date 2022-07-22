package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 机器人发送-链接消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotLinkMsgBodyBO {

    /**
     * 标题（必填）
     */
    private String title;

    /**
     * 内容（必填）
     */
    private String text;

    /**
     * 点击消息跳转的URL（必填）
     */
    private String messageUrl;

    /**
     * 图片URL
     */
    private String picUrl;



}
