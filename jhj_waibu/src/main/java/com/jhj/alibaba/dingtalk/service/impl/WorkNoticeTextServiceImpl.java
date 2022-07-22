package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.TextMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;

/**
 */
@Service("workNoticeTextService")
public class WorkNoticeTextServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        TextMsgBodyBO msgBody = (TextMsgBodyBO) t.getMsgBody();
        msg.getText().setContent(msgBody.getContent());
        return msg;
    }
}
