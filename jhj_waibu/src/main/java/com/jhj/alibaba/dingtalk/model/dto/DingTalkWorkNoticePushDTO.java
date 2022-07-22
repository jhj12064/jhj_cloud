package com.jhj.alibaba.dingtalk.model.dto;

import com.jhj.alibaba.dingtalk.enums.WorkNoticeTargetType;
import com.jhj.alibaba.dingtalk.enums.WorkNoticeType;
import com.jhj.alibaba.dingtalk.model.bo.MsgBodyBO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 发送钉钉工作消息DTO
 */
@Data
public class DingTalkWorkNoticePushDTO implements Serializable {

    /**
     * 接收者的userid列表，最大用户列表长度100。
     * user123,user456
     */
    private List<String> userIdList;

    /**
     * 接收者的部门id列表，最大列表长度20。
     * 123,345
     */
    private List<String> deptIdList;

    /**
     * 消息内容，最长不超过2048个字节
     */
    private MsgBodyBO msgBody;

    /**
     * 消息类型
     */
    private WorkNoticeType noticeType;

    /**
     * 接受目标类型
     */
    private WorkNoticeTargetType targetType;
}
