package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件消息
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileMsgBodyBO extends  MsgBodyBO{

    /**
     * 媒体文件ID，引用的媒体文件最大10MB。
     */
    private String mediaId;
}
