package com.jhj.alibaba.dingtalk.service;

import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;

/**
 */
public interface WorkNoticeService {

    /**
     * 发送工作通知
     *
     * @param t
     */
    void sendWorkNotice(DingTalkWorkNoticePushDTO t);

}
