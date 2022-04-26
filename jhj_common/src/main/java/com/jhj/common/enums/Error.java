package com.jhj.common.enums;

import lombok.Getter;

@Getter
public enum Error {

    /*  成功状态码*/
    SUCCESS(0, "请求成功"),

    NOT_IN_WHITE_LIST(310, "not in white list"),

    SC_UNAUTHORIZED_TOKEN_EXPIRED(401, "登录已过期，请重新登录"),

    NOT_FOUND_404(404, "404 not found"),

    /*未知错误*/
    FAIL(500, "系统开小差了"),

    JOB_ASYNC_FAIL(901, "异步job出了点错误"),

    NOT_FOUND_LOGIN_ACCOUNT(1004, "未查找到登录账号"),
    LOGIN_PASSWORD_INVALID(1005, "登录密码错误"),
    LOGIN_ACCOUNT_DISABLED(1006, "账号已被禁用"),
    LOGIN_ACCOUNT_NOT_FOUND_DINGTALK(1007, "未查询到钉钉账号"),
    LOGIN_ACCOUNT_NOT_BIND_DINGTALK(1008, "未绑定钉钉账号"),
    PASSWORD_ERROR(1009, "密码错误"),
    PERMS_DEFICIENCY(1017, "权限不足"),
    PARAM_INVALID(2001, "参数无效"),
    PARAM_IS_BLANK(2002, "必填项不能为空"),
    PARAM_TYPE_BIND_ERR(2003, "参数类型错误"),
    PARAM_TYPE_NO_EXIST_ERR(2004, "不存在该类型参数"),
    PARAM_SECRET_INVALID(2009, "secret 无效"),
    PARAM_AREA_LIMIT(2304, "地域限制"),
    PARAM_AREA_LEVEL_EXP(2304, "地域范围级别错误"),
    PARAM_PERM_NOTCONFORM_SYSPERMISSION(2601, "权限项的不符合要求"),

    SETTING_FIRST_ORDER_CYCLE_LIMIT_GREATER_ONEDAY(3013, "首单下单周期需大于0"),
    SETTING_CUSTOMER_COUNT_LIMIT_GREATER_NOTEMPTY(3014, "客户数限制需大于0"),

    NOT_FOUND_ACCOUNT(4011, "未查询到账号"),
    NOT_FOUND_SOURCE(4015, "未查询到该资源"),
    NOT_FOUND_UOP_ACCOUNT(4019, "未查询到开发者账号"),
    REPEAT_ACCOUNT(4020, "重复创建账号"),

    NOT_FOUNT_ROLE(5001, "未查询到角色信息"),
    NOT_FOUNT_ROLE_GROUP(5002, "未查询到角色组信息"),
    ROLE_MENUBAR_IS_BLANK(5004, "至少选择一个权限项"),
    ROLE_EXISTING_GROUP(5005, "角色已关联角色组"),
    ROLE_NOT_EXIST_GROUP(5006, "角色未关联角色组"),
    ROLE_LINK_EMP(5007, "角色存在绑定账号"),

    NOT_FOUNT_PERMISSION(6001, "未查询到权限项信息"),
    NOT_FOUNT_MENUBAR(6002, "未查询到菜单信息"),

    NOT_FOUND_DEPT(7001, "未查询到组织信息"),
    DT_CREATE_USER_EXIST(8001, "重复添加地推用户"),
    DT_CUSTOMER_LIMIT(8005, "员工客户数达到上限"),

    DING_TALK_NOT_FOUND_USER(9011, "未查询到钉钉用户信息"),
    DING_TALK_USER_EXTERNAL(9012, "外部用户暂不支持操作"),
    DING_TALK_REPEAT_BIND(9013, "重复绑定钉钉"),

    NOT_FOUND_OAMEMBER(10001, "未查询到oa账号信息"),
    REPEAT_OAMEMBER(10002, "重复绑定oa账号"),

    NOT_FOUND_DITUI_TARGET(11011, "未查询到目标信息"),
    EMPTY_TARGET(11012, "目标数据为空"),

    /**
     * rmp 风控平台
     */
    RULE_NOT_RET(22005, "未设置规则输出"),

    NOT_FOUND_RULE(22001, "未查询到规则"),

    EVENT_NOT_FOUND(22006, "未查询到事件"),
    EVENT_NAME_EXIST(22007, "事件名称已经存在"),

    NOT_FOUND_JOB_GROUP(31001, "未查询到执行器"),
    NOT_FOUND_JOB(31002, "未查询到任务信息"),
    REPEAT_JOB_GROUP(31003, "执行器名称重复"),

    /**
     * oms 订单平台
     */
    NOT_FOUND_RECONCILIATION(23001, "未查询到对账单"),
    CAN_NOT_OPERATE(23002, "当前状态不允许做该操作"),
    NOT_FOUND_INVOICE(23001, "未查询到发票"),
    NOT_FOUND_SUPPLIER(23002, "未查询到供应商"),
    EXIST_INVOICE_NO(23002, "发票号已存在"),
    ERROR_INVOICE_AMOUNT(23003, "发票总额与各发票之和不等"),
    REPEAT_INVOICE_NO(23004, "发票号重复了"),
    /**
     * crm平台（商机）
     */
    NOT_FOUND_BUSINESSOPPORTUNITY(24001, "未查询到该商机"),
    NOT_FOUND_BUSTOPTRACK(24002, "未查询到该跟踪"),
    NOT_ALLOW_DISTRIBUTE(24003, "不允许当前登录人员进行分配"),
    NOT_FOUND_CUSTOMER(24004, "未查询到该客户"),
    NOT_FOUND_LINKMAN(24005, "未查询到该联系人"),
    NOT_FOUND_FEATURE(24006, "未查询到该客户特点"),
    NOT_FOUND_CUSTOMERACCOUNT(24007, "未查询到该客户账户"),
    NOT_ALLOW_CREATECUSTOMER(24008,"不允许重复创建"),
    TO_OA_ERROR(24009,"数据同步至OA报错"),
    TO_OA_URL_ERROR(24010,"网络异常,分配商机并同步至OA失败，分配回退"),


    /**
     * nat
     */
    DING_TALK_ROBOT_ERROR(24001, "钉钉机器人报错"),
    DING_TALK_WORK_NOTICE_ERROR(24001, "钉钉工作通知报错"),
    DING_TALK_ROBOT_MSG_SIGN_ERROR(24001, "钉钉机器人消息校验失败"),
    /**
     * BI
     */
    NOT_FOUND_SYNC_FAIL_LOG(25001, "为查询到记录"),
    SYNC_FAIL_LOG_ALREADY_SUCCESS(25001, "该记录已经重试成功"),
    SYNC_FAIL_LOG_RETRY_TO_MORE(25001, "该记录重试次数超过上限"),
    ;
    int code;
    String msg;

    Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
