package com.jhj.alibaba.dingtalk.service.impl;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.jhj.alibaba.dingtalk.model.bo.ImageMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import org.springframework.stereotype.Service;

/**
 * @author Jeremy
 * @date 2022/2/23 16:40
 */
@Service("workNoticeImageService")
public class WorkNoticeImageServiceImpl extends AbsWorkNotice {

    @Override
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        this.send(t);
    }

    @Override
    protected OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t) {
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(t.getNoticeType().getCode());
        msg.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
        ImageMsgBodyBO msgBody = (ImageMsgBodyBO) t.getMsgBody();
        msg.getImage().setMediaId(msgBody.getMediaId());
        return msg;
    }
}
