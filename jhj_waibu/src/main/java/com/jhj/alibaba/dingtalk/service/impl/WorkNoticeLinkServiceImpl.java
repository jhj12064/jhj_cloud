package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.LinkMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;

/**
 * @author Jeremy
 * @date 2022/2/23 16:40
 */
@Service("workNoticeLinkService")
public class WorkNoticeLinkServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        LinkMsgBodyBO msgBody = (LinkMsgBodyBO) t.getMsgBody();
        msg.getLink().setTitle(msgBody.getTitle());
        msg.getLink().setText(msgBody.getText());
        msg.getLink().setMessageUrl(msgBody.getMessageUrl());
        msg.getLink().setPicUrl(msgBody.getPicUrl());
        return msg;
    }
}
