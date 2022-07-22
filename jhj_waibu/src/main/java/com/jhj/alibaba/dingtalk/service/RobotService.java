package com.jhj.alibaba.dingtalk.service;

import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;

/**
 */
public interface RobotService {

    /**
     * 钉钉机器人发送群聊消息
     *
     * @param t
     * @param webhook 地址
     * @param isSign  是否需要加签
     * @param secret  加签秘钥 , 需要加签时必填
     */
    void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret);

    /**
     * 钉钉机器人发送单聊消息
     *
     * @param t
     */
    void sendSampleMsg(DingTalkRobotSampleMsgDTO t);

}
