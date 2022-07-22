package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TextMsgBodyBO extends  MsgBodyBO{

    /**
     * 消息内容，建议500字符以内
     */
    private String content;
}
