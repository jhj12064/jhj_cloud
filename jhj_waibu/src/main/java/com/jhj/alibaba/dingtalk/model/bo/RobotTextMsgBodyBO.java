package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 机器人发送-文本消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotTextMsgBodyBO {

    /**
     * 消息内容
     */
    private String content;

}
