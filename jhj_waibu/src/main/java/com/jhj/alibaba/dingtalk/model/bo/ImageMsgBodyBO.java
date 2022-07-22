package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageMsgBodyBO extends  MsgBodyBO{

    /**
     * 媒体文件ID
     */
    private String mediaId;
}
