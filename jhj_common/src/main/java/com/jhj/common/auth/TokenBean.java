package com.jhj.common.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("登录凭 证信息")
@AllArgsConstructor
@NoArgsConstructor
public class TokenBean implements Serializable {

    private static final long serialVersionUID = -2792131319736056219L;

    @ApiModelProperty("accesstoken")
    private String accessToken;

    @ApiModelProperty("refreshToken")
    private String refreshToken;

    @ApiModelProperty("token有效时长")
    private long expireIn;

    @ApiModelProperty("过期时间")
    private long expireTime;

}
