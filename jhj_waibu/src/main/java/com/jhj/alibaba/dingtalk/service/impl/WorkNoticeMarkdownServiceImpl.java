package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.MarkdownMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;

/**
 * @author Jeremy
 * @date 2022/2/23 16:40
 */
@Service("workNoticeMarkdownService")
public class WorkNoticeMarkdownServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
        MarkdownMsgBodyBO msgBody = (MarkdownMsgBodyBO) t.getMsgBody();
        msg.getMarkdown().setText(msgBody.getText());
        msg.getMarkdown().setTitle(msgBody.getTitle());
        return msg;
    }
}
