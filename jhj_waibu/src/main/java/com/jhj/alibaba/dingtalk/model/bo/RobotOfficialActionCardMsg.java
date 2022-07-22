package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 卡片消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotOfficialActionCardMsg {

    private String title;

    private String text;

    private String singleTitle;

    private String singleURL;
}
