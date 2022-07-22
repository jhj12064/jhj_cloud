package com.jhj.alibaba.dingtalk.model.dto;

import com.jhj.alibaba.dingtalk.enums.RobotSampleMsgType;
import com.jhj.alibaba.dingtalk.model.bo.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 单聊消息
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DingTalkRobotSampleMsgDTO implements Serializable {

    /**
     * 消息类型
     */
    private RobotSampleMsgType msgtype;

    /**
     * 机器人的robotCode
     * 也就是appkey
     */
    public String robotCode;

    /**
     * 被推送会话人员的userId列表
     */
    public List<String> userIds;


    private RobotTextMsgBodyBO text;

    private RobotMarkdownMsgBodyBO markdown;

    private RobotLinkMsgBodyBO link;

    private RobotImageMsgBodyBO image;

    /**
     * 卡片
     */
    private RobotOfficialActionCardMsg robotOfficialActionCardMsg;

    private RobotOfficialActionCardMsg1 robotOfficialActionCardMsg1;

    private RobotOfficialActionCardMsg2 robotOfficialActionCardMsg2;

    private RobotSampleActionCard robotSampleActionCard;

    private RobotSampleActionCard2 robotSampleActionCard2;

    private RobotSampleActionCard3 robotSampleActionCard3;


}
