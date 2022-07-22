package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.VoiceMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;

/**
 */
@Service("workNoticeVoiceService")
public class WorkNoticeVoiceServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setVoice(new OapiMessageCorpconversationAsyncsendV2Request.Voice());
        VoiceMsgBodyBO msgBody = (VoiceMsgBodyBO) t.getMsgBody();
        msg.getVoice().setMediaId(msgBody.getMediaId());
        msg.getVoice().setDuration(msgBody.getDuration() != null ? String.valueOf(msgBody.getDuration()) : "0");
        return msg;
    }
}
