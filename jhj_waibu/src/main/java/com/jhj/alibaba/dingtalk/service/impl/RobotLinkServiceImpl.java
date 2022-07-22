package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.bo.RobotLinkMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotLinkService")
public class RobotLinkServiceImpl extends AbsRobot {

    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        this.groupChat(t, webhook, isSign, secret);
    }


    @Override
    public OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(t.getMsgtype().getCode());
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        RobotLinkMsgBodyBO msgBody = t.getLink();
        link.setMessageUrl(msgBody.getMessageUrl());
        link.setPicUrl(msgBody.getPicUrl());
        link.setTitle(msgBody.getTitle());
        link.setText(msgBody.getText());
        request.setLink(link);
        return request;
    }

    @Override
    public void sendSampleMsg(DingTalkRobotSampleMsgDTO t) {
        this.sampleMsg(t);
    }

    @Override
    protected BatchSendOTORequest buildBatchSendOTORequest(DingTalkRobotSampleMsgDTO t, List<String> userIds) {
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(t.getRobotCode())
                .setUserIds(userIds)
                .setMsgKey(t.getMsgtype().getCode())
                .setMsgParam(JSON.toJSONString(t.getLink()));
        return batchSendOTORequest;
    }

}
