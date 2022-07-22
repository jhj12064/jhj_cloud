package com.jhj.alibaba.dingtalk.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * OA消息
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OaMsgBodyBO extends MsgBodyBO {

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接。
     */
    private String messageUrl;

    /**
     * PC端点击消息时跳转到的地址。
     */
    private String pcMessageUrl;

    /**
     * 消息头部
     */
    private Head head;

    /**
     * 消息状态栏，只支持接收者的userid列表，userid最多不能超过5个人。
     */
    private StatusBar statusBar;

    /**
     * 消息体
     */
    private Body body;


    @Data
    public static class Head {
        /**
         * 消息头部的背景颜色
         */
        private String bgColor;

        /**
         * 头部标题
         */
        private String text;
    }

    @Data
    public static class StatusBar {
        /**
         * 状态栏文案
         */
        private String statusValue;

        /**
         * 状态栏背景色，默认为黑色，推荐0xFF加六位颜色值。
         */
        private String statusBg;
    }

    @Data
    public static class Body {
        /**
         * 正文标题
         */
        private String title;

        /**
         * 消息体的表单，最多显示6个，超过会被隐藏
         */
        private List<Form> forms;

        /**
         * 单行富文本信息
         */
        private Rich rich;

        /**
         * 消息体的内容，最多显示3行。
         */
        private String content;

        /**
         * 消息体中的图片，支持图片资源@mediaId。建议宽600像素 x 400像素，宽高比3 : 2。
         *
         * @lADOADmaWMzazQKA
         */
        private String image;

        /**
         * 自定义的附件数目
         */
        private String fileCount;

        /**
         * 自定义的作者名字
         */
        private String author;
    }

    @Data
    public static class Rich {
        /**
         * 单行富文本信息的数目
         */
        private String num;

        /**
         * 单行富文本信息的单位
         */
        private String unit;
    }

    @Data
    public static class Form {
        /**
         * 消息体的关键字
         */
        private String key;

        /**
         * 消息体的关键字对应的值
         */
        private String value;
    }

}


