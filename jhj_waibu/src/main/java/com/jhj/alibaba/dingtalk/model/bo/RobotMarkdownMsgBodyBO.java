package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 机器人发送-Markdown消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotMarkdownMsgBodyBO {

    /**
     * 首屏会话透出的展示内容（必填）
     */
    private String title;

    /**
     * markdown格式的消息（必填）
     */
    private String text;

}
