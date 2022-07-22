package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.ActionCardMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 16:40
 */
@Service("workNoticeActionCardService")
public class WorkNoticeActionCardServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        ActionCardMsgBodyBO msgBody = (ActionCardMsgBodyBO) t.getMsgBody();
        msg.getActionCard().setTitle(msgBody.getTitle());
        msg.getActionCard().setMarkdown(msgBody.getMarkdown());
        msg.getActionCard().setSingleTitle(msgBody.getSingleTitle());
        msg.getActionCard().setSingleUrl(msgBody.getSingleUrl());
        if (StringUtils.isNotBlank(msgBody.getBtnOrientation()) && !CollectionUtils.isEmpty(msgBody.getBtnJsonList())) {
            List<OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList> btnList = new ArrayList<>();
            for (ActionCardMsgBodyBO.BtnJson btnJson : msgBody.getBtnJsonList()) {
                OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList btnJsonList = new OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList();
                btnJsonList.setActionUrl(btnJson.getActionUrl());
                btnJsonList.setTitle(btnJson.getTitle());
                btnList.add(btnJsonList);
            }
            msg.getActionCard().setBtnOrientation(msgBody.getBtnOrientation());
            msg.getActionCard().setBtnJsonList(btnList);
        }
        return msg;
    }
}
