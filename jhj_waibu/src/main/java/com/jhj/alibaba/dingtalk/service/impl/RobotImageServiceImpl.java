package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 14:38
 */
@Service("robotImageService")
public class RobotImageServiceImpl extends AbsRobot {

    /**
     * 群聊消息不支持
     *
     */
    @Override
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {}

    /**
     * 群聊消息不支持
     *
     */
    @Override
    protected OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t) {
        return null;
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
                .setMsgParam(JSON.toJSONString(t.getImage()));
        return batchSendOTORequest;
    }


}
