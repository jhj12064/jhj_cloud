package com.jhj.alibaba.dingtalk.enums;

public enum WorkNoticeTargetType {
    USER("user", "用户"),
    DEPT("dept", "部门")
    ;

    private String code;

    private String describe;

    WorkNoticeTargetType(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
