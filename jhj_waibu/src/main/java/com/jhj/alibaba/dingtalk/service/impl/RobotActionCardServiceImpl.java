package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.bo.RobotActionCardMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotActionCardService")
public class RobotActionCardServiceImpl extends AbsRobot {

    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        this.groupChat(t, webhook, isSign, secret);
    }


    @Override
    public OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(t.getMsgtype().getCode());
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        RobotActionCardMsgBodyBO msgBody = t.getActionCard();
        actionCard.setBtnOrientation(msgBody.getBtnOrientation());
        if (!CollectionUtils.isEmpty(msgBody.getBtns())) {
            actionCard.setBtns(msgBody.getBtns().stream().map(e -> {
                OapiRobotSendRequest.Btns btn = new OapiRobotSendRequest.Btns();
                btn.setActionURL(e.getActionURL());
                btn.setTitle(e.getTitle());
                return btn;
            }).collect(Collectors.toList()));
        }
        actionCard.setText(msgBody.getText());
        actionCard.setTitle(msgBody.getTitle());
        actionCard.setSingleTitle(msgBody.getSingleTitle());
        actionCard.setSingleURL(msgBody.getSingleURL());
        request.setActionCard(actionCard);
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
                .setMsgKey(t.getMsgtype().getCode());
        switch (t.getMsgtype()) {
            case officialActionCardMsg:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotOfficialActionCardMsg()));
                break;
            case officialActionCardMsg1:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotOfficialActionCardMsg1()));
                break;
            case officialActionCardMsg2:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotOfficialActionCardMsg2()));
                break;
            case sampleActionCard:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotSampleActionCard()));
                break;
            case sampleActionCard2:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotSampleActionCard2()));
                break;
            case sampleActionCard3:
                batchSendOTORequest.setMsgParam(JSON.toJSONString(t.getRobotSampleActionCard3()));
                break;
            default:
                break;
        }
        return batchSendOTORequest;
    }


}
