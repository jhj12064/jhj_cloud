package com.jhj.common.enums;

import lombok.Getter;

@Getter
public enum Error {

    /*  成功状态码*/
    SUCCESS(0, ""),

    NOT_IN_WHITE_LIST(310, "not in  list"),

    SC_UNAUTHORIZED_TOKEN_EXPIRED(401, "登录已过期，"),

    NOT_FOUND_404(404, "404  found"),

    /*未知错误*/
    FAIL(500, ""),

    JOB_ASYNC_FAIL(901, ""),

    ;
    int code;
    String msg;

    Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
