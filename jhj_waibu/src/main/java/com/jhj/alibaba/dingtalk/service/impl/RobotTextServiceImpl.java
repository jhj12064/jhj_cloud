package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.bo.RobotTextMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotTextService")
public class RobotTextServiceImpl extends AbsRobot {

    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        this.groupChat(t, webhook, isSign, secret);
    }


    @Override
    public OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(t.getMsgtype().getCode());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        RobotTextMsgBodyBO msgBody = t.getText();
        text.setContent(msgBody.getContent());
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(t.getAt().getAtMobiles());
        at.setIsAtAll(t.getAt().getIsAtAll());
        at.setAtUserIds(t.getAt().getAtUserIds());
        request.setAt(at);
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
                .setMsgParam(JSON.toJSONString(t.getText()));
        return batchSendOTORequest;
    }


}
