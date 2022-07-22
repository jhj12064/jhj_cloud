package com.jhj.alibaba.dingtalk.service.impl;

import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.bo.RobotFeedCardMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotFeedCardService")
public class RobotFeedCardServiceImpl extends AbsRobot {

    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        this.groupChat(t, webhook, isSign, secret);
    }


    @Override
    public OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(t.getMsgtype().getCode());
        OapiRobotSendRequest.Feedcard feedCard = new OapiRobotSendRequest.Feedcard();
        RobotFeedCardMsgBodyBO msgBody = t.getFeedCard();
        feedCard.setLinks(msgBody.getLinks().stream().map(e -> {
            OapiRobotSendRequest.Links links = new OapiRobotSendRequest.Links();
            links.setMessageURL(e.getMessageURL());
            links.setPicURL(e.getPicURL());
            links.setTitle(e.getTitle());
            return links;
        }).collect(Collectors.toList()));
        request.setFeedCard(feedCard);
        return request;
    }

    /**
     * 单聊消息不支持
     *
     * @param t
     */
    @Override
    public void sendSampleMsg(DingTalkRobotSampleMsgDTO t) {

    }

    /**
     * 单聊消息不支持
     *
     * @param t
     */
    @Override
    protected BatchSendOTORequest buildBatchSendOTORequest(DingTalkRobotSampleMsgDTO t, List<String> userIds) {
        return null;
    }

}
