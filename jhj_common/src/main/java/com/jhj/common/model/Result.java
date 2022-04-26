package com.jhj.common.model;

import com.alibaba.fastjson.JSONObject;
import com.jhj.common.enums.Error;
import com.jhj.common.exception.GlobalException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Result extends JSONObject implements Serializable, Cloneable {

    private Object data;
    private long size;
    private Integer code;
    private String msg;

    private static final long serialVersionUID = -2381194749816902715L;

    private static volatile Result result = new Result();

    public static Result getInstance() {
//        if (result == null) {
//            synchronized (Result.class) {
//                if (result == null) {
//                    result = new Result();
//                }
//            }
//        }
        return new Result();
    }

    public Result success() {
        this.put("code", Error.SUCCESS.getCode());
        this.put("msg", Error.SUCCESS.getMsg());
        return this;
    }

    public Result success(Object obj) {
        this.success();
        if (null != obj) {
            this.put("data", obj);
        }
        return this;
    }

    public Result success(Object obj, long size) {
        success(obj);
        this.put("size", size);
        return this;
    }

    public Result fail(Error err) {
        this.put("code", err.getCode());
        this.put("msg", err.getMsg());
        return this;
    }

    public Result fail(Error err, String msg) {
        this.put("code", err.getCode());
        this.put("msg", msg);
        return this;
    }

    public Result fail(String msg) {
        this.put("code", Error.FAIL);
        this.put("msg", msg);
        return this;
    }

    public Result fail(GlobalException gex) {
        this.put("code", gex.getCode());
        this.put("msg", gex.getMessage());
        return this;
    }

    public JSONObject getData() {
        return this.getJSONObject("data");
    }
}
