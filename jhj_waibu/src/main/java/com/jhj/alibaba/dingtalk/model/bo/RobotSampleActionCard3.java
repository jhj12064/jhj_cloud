package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 卡片消息 3个按钮
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotSampleActionCard3 {
    private String title;
    private String text;
    private String actionTitle1;
    private String actionURL1;
    private String actionTitle2;
    private String actionURL2;
    private String actionTitle3;
    private String actionURL3;
}
