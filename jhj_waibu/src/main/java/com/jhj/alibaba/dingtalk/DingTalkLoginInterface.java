package com.jhj.alibaba.dingtalk;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetbyunionidResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.jhj.alibaba.dingtalk.model.DingTalkUserVO;
import com.jhj.utils.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.internal.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.*;

/**
 */
@Slf4j
@Component

public class DingTalkLoginInterface {

    public final String CLASS_NAME = this.getClass().getName();

    @Autowired
    private CacheHelper cache;

    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;

    @Value("${aliyun.dingTalk.appKey}")
    private String appKey;

    @Value("${aliyun.dingTalk.appSecret}")
    private String appSecret;

    /**
     * 扫码登录，获取用户信息
     *
     * @param code 临时授权码
     * @return
     * @throws Exception
     */
    public DingTalkUserVO getUserInfo(String code) {
        String methodName = "getUserInfo()";
        try {
            log.info("{},{},code:{}", CLASS_NAME, methodName, code);
            //参数校验
            if (StringUtils.isBlank(code)) {
                //throw new GlobalException(Error.PARAM_IS_BLANK);
            }
            // 获取access_token
            String accessToken = dingTalkTokenInterface.getAccessToken();
            // 通过临时授权码获取授权用户的个人信息
            OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = this.getUserinfoByCodeRequest(code);
            // 通过unionId获取userId
            OapiUserGetbyunionidResponse.UserGetByUnionIdResponse dingUser = this.getUserIdByUnionId(userInfo.getUnionid(), accessToken);
            if (dingUser.getContactType() == 1) {
                //throw new GlobalException(Error.DING_TALK_USER_EXTERNAL);
            }
            // 根据userId和token 获取用户信息
            OapiV2UserGetResponse.UserGetResponse userDeatil = this.getUserInfoByUserId(dingUser.getUserid(), accessToken);
            DingTalkUserVO dingTalkUserVO = new DingTalkUserVO();
            BeanCopyUtil.copyPropertiesIgnoreNull(userDeatil, dingTalkUserVO);
            dingTalkUserVO.setUserId(userDeatil.getUserid());
            dingTalkUserVO.setNick(userInfo.getNick());
            dingTalkUserVO.setOpenId(userInfo.getOpenid());
            dingTalkUserVO.setUnionId(userInfo.getUnionid());
            return dingTalkUserVO;
        } catch (Exception e) {
            log.error("{},{},获取用户信息异常：{}", CLASS_NAME, methodName, e.getMessage());
            return null;
        }
    }


    /**
     * 通过临时授权码获取授权用户的个人信息
     *
     * @param code 临时授权码
     * @return 用户的个人信息
     * @throws Exception
     */
    private OapiSnsGetuserinfoBycodeResponse.UserInfo getUserinfoByCodeRequest(String code) throws Exception {
        String methodName = "getUserinfoByCodeRequest()";
        // 通过临时授权码获取授权用户的个人信息
        DefaultDingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GETUSERINFO_BYCODE);
        OapiSnsGetuserinfoBycodeRequest reqBycodeRequest = new OapiSnsGetuserinfoBycodeRequest();
        reqBycodeRequest.setTmpAuthCode(code);
        OapiSnsGetuserinfoBycodeResponse oapiSnsGetuserinfoBycodeResponse = client.execute(reqBycodeRequest, appKey, appSecret);
        log.info("{},{},oapiSnsGetuserinfoBycodeResponse:{}", CLASS_NAME, methodName, JSON.toJSONString(oapiSnsGetuserinfoBycodeResponse));
        if (oapiSnsGetuserinfoBycodeResponse == null || !DingTalkConstant.SUCCESS_CODE.equals(oapiSnsGetuserinfoBycodeResponse.getErrorCode())) {
            throw new Exception("getUserinfoByCodeRequest error");
        }
        return oapiSnsGetuserinfoBycodeResponse.getUserInfo();
    }

    /**
     * 获取userId
     *
     * @param unionId     unionId
     * @param accessToken 获取access_token
     * @return userId
     * @throws Exception
     */
    private OapiUserGetbyunionidResponse.UserGetByUnionIdResponse getUserIdByUnionId(String unionId, String accessToken) throws Exception {
        String methodName = "getUserIdByUnionId()";
        DingTalkClient clientDingTalkClient = new DefaultDingTalkClient(CLIENT_URL_GET_BYUNIONID);
        OapiUserGetbyunionidRequest reqGetByUnionIdRequest = new OapiUserGetbyunionidRequest();
        reqGetByUnionIdRequest.setUnionid(unionId);
        OapiUserGetbyunionidResponse oapiUserGetByUnionIdResponse = clientDingTalkClient.execute(reqGetByUnionIdRequest, accessToken);
        log.info("{},{},oapiUserGetByUnionIdResponse:{}", CLASS_NAME, methodName, JSON.toJSONString(oapiUserGetByUnionIdResponse));
        if (oapiUserGetByUnionIdResponse == null || !DingTalkConstant.SUCCESS_CODE.equals(oapiUserGetByUnionIdResponse.getErrorCode())) {
            throw new Exception("getUserIdByUnionId error");
        }
        return oapiUserGetByUnionIdResponse.getResult();
    }

    /**
     * 根据userId和token 获取用户信息
     *
     * @param userId      用户id
     * @param accessToken token
     * @return 用户信息
     * @throws Exception
     */
    private OapiV2UserGetResponse.UserGetResponse getUserInfoByUserId(String userId, String accessToken) throws Exception {
        String methodName = "getUserInfoByUserId()";
        DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GETUSERINFO_BYUSERID);
        OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
        reqGetRequest.setUserid(userId);
        reqGetRequest.setLanguage(LANGUAGE_CN);
        OapiV2UserGetResponse rspGetResponse = client.execute(reqGetRequest, accessToken);
        log.info("{},{},rspGetResponse:{}", CLASS_NAME, methodName, JSON.toJSONString(rspGetResponse));
        if (rspGetResponse == null || !DingTalkConstant.SUCCESS_CODE.equals(rspGetResponse.getErrorCode())) {
            throw new Exception("getUserInfoByUserId error");
        }
        return rspGetResponse.getResult();
    }


}
