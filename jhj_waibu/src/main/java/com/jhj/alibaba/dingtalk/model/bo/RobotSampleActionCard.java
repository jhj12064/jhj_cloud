package com.jhj.alibaba.dingtalk.model.bo;

import lombok.*;

/**
 * 卡片消息 1个按钮
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotSampleActionCard {
    private String title;
    private String text;
    private String singleTitle;
    private String singleURL;
}
