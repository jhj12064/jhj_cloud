package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * markdown消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkdownMsgBodyBO extends  MsgBodyBO{

    /**
     * 首屏会话透出的展示内容。
     */
    private String title;

    /**
     * markdown格式的消息，最大不超过5000字符。
     */
    private String text;
}
