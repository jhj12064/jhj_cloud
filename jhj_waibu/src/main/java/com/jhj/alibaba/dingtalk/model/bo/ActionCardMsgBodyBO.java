package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 卡片消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ActionCardMsgBodyBO extends MsgBodyBO {

    /**
     * 透出到会话列表和通知的文案。
     */
    private String title;

    /**
     * 消息内容，支持markdown，语法参考标准markdown语法。建议1000个字符以内。
     */
    private String markdown;

    /**
     * 使用整体跳转ActionCard样式时的标题。必须与single_url同时设置，最长20个字符。
     * 如果是整体跳转的ActionCard样式，则single_title和single_url必须设置。
     */
    private String singleTitle;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符。
     */
    private String singleUrl;

    /**
     * 使用独立跳转ActionCard样式时的按钮排列方式：
     * 0：竖直排列
     * 1：横向排列
     * 必须与btn_json_list同时设置。
     */
    private String btnOrientation;

    /**
     * 使用独立跳转ActionCard样式时的按钮列表；必须与btn_orientation同时设置，且长度不超过1000字符。
     *
     * 如果是独立跳转的ActionCard样式，则btn_json_list和btn_orientation必须设置
     */
    private List<BtnJson> btnJsonList;

    @Data
    public static class BtnJson {
        /**
         * 使用独立跳转ActionCard样式时的按钮的标题，最长20个字符。
         */
        private String title;

        /**
         * 使用独立跳转ActionCard样式时的跳转链接。
         */
        private String actionUrl;
    }

}


