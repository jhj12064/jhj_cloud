package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.OaMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 16:40
 */
@Service("workNoticeOaService")
public class WorkNoticeOaServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        OaMsgBodyBO msgBody = (OaMsgBodyBO) t.getMsgBody();
        msg.setMsgtype(t.getNoticeType().getCode());
        OapiMessageCorpconversationAsyncsendV2Request.OA oa = new OapiMessageCorpconversationAsyncsendV2Request.OA();
        oa.setMessageUrl(msgBody.getMessageUrl());
        oa.setPcMessageUrl(msgBody.getPcMessageUrl());
        if (msgBody.getHead() != null) {
            OapiMessageCorpconversationAsyncsendV2Request.Head head = new OapiMessageCorpconversationAsyncsendV2Request.Head();
            head.setBgcolor(msgBody.getHead().getBgColor());
            head.setText(msgBody.getHead().getText());
            oa.setHead(head);
        }
        if (msgBody.getStatusBar() != null) {
            OapiMessageCorpconversationAsyncsendV2Request.StatusBar statusBar = new OapiMessageCorpconversationAsyncsendV2Request.StatusBar();
            statusBar.setStatusBg(msgBody.getStatusBar().getStatusBg());
            statusBar.setStatusValue(msgBody.getStatusBar().getStatusValue());
            oa.setStatusBar(statusBar);
        }
        if (msgBody.getBody() != null) {
            OapiMessageCorpconversationAsyncsendV2Request.Body body = new OapiMessageCorpconversationAsyncsendV2Request.Body();
            body.setAuthor(msgBody.getBody().getAuthor());
            body.setContent(msgBody.getBody().getContent());
            body.setFileCount(msgBody.getBody().getFileCount());
            body.setImage(msgBody.getBody().getImage());
            body.setTitle(msgBody.getBody().getTitle());
            if (msgBody.getBody().getRich() != null) {
                OapiMessageCorpconversationAsyncsendV2Request.Rich rich = new OapiMessageCorpconversationAsyncsendV2Request.Rich();
                rich.setNum(msgBody.getBody().getRich().getNum());
                rich.setUnit(msgBody.getBody().getRich().getUnit());
                body.setRich(rich);
            }
            if (!CollectionUtils.isEmpty(msgBody.getBody().getForms())) {
                List<OapiMessageCorpconversationAsyncsendV2Request.Form> forms = new ArrayList<>();
                for (OaMsgBodyBO.Form f : msgBody.getBody().getForms()) {
                    OapiMessageCorpconversationAsyncsendV2Request.Form form = new OapiMessageCorpconversationAsyncsendV2Request.Form();
                    form.setKey(f.getKey());
                    form.setValue(f.getValue());
                    forms.add(form);
                }
                body.setForm(forms);
            }
            oa.setBody(body);
        }
        msg.setOa(oa);
        return msg;
    }
}
