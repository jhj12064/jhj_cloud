package com.jhj.alibaba.dingtalk;

import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import com.jhj.alibaba.dingtalk.service.WorkNoticeService;
import com.jhj.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 发送钉钉工作消息
 *
 */
@Slf4j
@Component
public class DingTalkSendWorkNoticeInterface {


    /**
     * 发送工作通知
     *
     * @param t
     */
    public void sendWorkNotice(DingTalkWorkNoticePushDTO t) {
        WorkNoticeService workNoticeService = SpringContextUtils.getBean(t.getNoticeType().getServiceName(), WorkNoticeService.class);
        workNoticeService.sendWorkNotice(t);
    }


}
