package com.jhj.alibaba.dingtalk.constant;


public class DingTalkConstant {

    /**
     * 返回成功code
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 国际化
     */
    public static final String LANGUAGE_CN = "zh_CN";//中文

    public static final String LANGUAGE_US = "en_US";//英文

    /**
     * 根部门Id
     */
    public static final Long DEPT_ID_ROOT = 1l;

    /**
     * 调用地址
     */
    public static final String CLIENT_URL_GET_CORP_TOKEN = "https://oapi.dingtalk.com/gettoken";//获取企业授权token

    public static final String CLIENT_URL_GETUSERINFO_BYCODE = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";//通过临时码获取用户信息

    public static final String CLIENT_URL_GET_BYUNIONID = "https://oapi.dingtalk.com/topapi/user/getbyunionid";//通过unionId获取用户信息

    public static final String CLIENT_URL_GETUSERINFO_BYUSERID = "https://oapi.dingtalk.com/topapi/v2/user/get";//通过userId获取用户信息

    public static final String CLIENT_URL_GET_DEPARTMENT_LISTSUB = "https://oapi.dingtalk.com/topapi/v2/department/listsub";//获取部门列表  调用本接口获取下一级部门基础信息

    //获取部门用户基础信息
    public static final String CLIENT_URL_GET_DEPARTMENT_USER = "https://oapi.dingtalk.com/topapi/user/listsimple";
    //发送工作通知
    public static final String CLIENT_URL_SEND_WORK_NOTICE = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";
    //上传媒体文件
    public static final String CLIENT_URL_UPLOAD_MEDIA = "https://oapi.dingtalk.com/media/upload";
    //钉钉机器人发送
    public static final String CLIENT_URL_ROBOT_SEND_MSG = "https://oapi.dingtalk.com/robot/send?access_token=";
}
