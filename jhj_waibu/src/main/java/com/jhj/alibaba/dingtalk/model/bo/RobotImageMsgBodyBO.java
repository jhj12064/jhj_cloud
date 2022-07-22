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
public class RobotImageMsgBodyBO {


    /**
     * 图片URL
     */
    private String photoURL;



}
