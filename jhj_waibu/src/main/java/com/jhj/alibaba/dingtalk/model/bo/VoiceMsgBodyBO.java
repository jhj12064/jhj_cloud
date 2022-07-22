package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 语音消息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VoiceMsgBodyBO extends  MsgBodyBO{

    /**
     * 媒体文件ID
     */
    private String mediaId;

    /**
     * 正整数，小于60，表示音频时长
     */
    private Integer duration;

}
