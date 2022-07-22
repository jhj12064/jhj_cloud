package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 卡片消息 2个按钮
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotSampleActionCard2 {
    private String title;
    private String text;
    private String actionTitle1;
    private String actionURL1;
    private String actionTitle2;
    private String actionURL2;

}
