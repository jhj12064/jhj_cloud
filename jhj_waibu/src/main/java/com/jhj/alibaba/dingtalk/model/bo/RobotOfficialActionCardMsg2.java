package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 卡片消息 Button竖向排列
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotOfficialActionCardMsg2 {

    private String title;

    private String text;

    private String buttonTitle1;

    private String buttonUrl1;

    private String buttonTitle2;

    private String buttonUrl2;
}
