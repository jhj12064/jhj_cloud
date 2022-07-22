package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 链接消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkMsgBodyBO extends  MsgBodyBO{

    /**
     * 消息点击链接地址 当发送消息为小程序时支持小程序跳转链接。
     * http://dingtalk.com
     * 必填
     */
    private String messageUrl;

    /**
     * 图片链接
     * @lADOADmaWMzazQKA
     * 必填
     */
    private String picUrl;

    /**
     * 消息标题，建议100字符以内。
     * 必填
     */
    private String title;

    /**
     * 消息描述，建议500字符以内
     * 必填
     */
    private String text;
}
