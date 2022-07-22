package com.jhj.alibaba.dingtalk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DingTalkUserVO implements Serializable {

    private Boolean active;
    private Boolean admin;
    private String avatar;
    private Boolean boss;
    private Boolean senior;
    private String mobile;
    private String nick;
    private String openId;
    private String title;
    private String unionId;
    private String userId;
}
