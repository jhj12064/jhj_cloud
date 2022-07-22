package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.bo.RobotMarkdownMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotMarkdownService")
public class RobotMarkdownServiceImpl extends AbsRobot {

    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        this.groupChat(t, webhook, isSign, secret);
    }


    @Override
    public OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(t.getMsgtype().getCode());
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        RobotMarkdownMsgBodyBO msgBody = t.getMarkdown();
        markdown.setTitle(msgBody.getTitle());
        markdown.setText(msgBody.getText());
        request.setMarkdown(markdown);
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
                .setMsgParam(JSON.toJSONString(t.getMarkdown()));
        return batchSendOTORequest;
    }

}
