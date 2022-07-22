package com.jhj.alibaba.dingtalk;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.internal.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.CLIENT_URL_GET_CORP_TOKEN;

//import com.aliyun.dingtalkoauth2_1_0.models.*;
//import com.aliyun.teaopenapi.models.*;

/**
 */
@Slf4j
@Component
public class DingTalkTokenInterface {


    @Autowired
    private CacheHelper cache;

    @Value("${aliyun.dingTalk.appKey}")
    private String appKey;

    @Value("${aliyun.dingTalk.appSecret}")
    private String appSecret;


    /**
     * 获取token
     *
     * @return 获取access_token
     * @throws Exception
     */
    public String getAccessToken() throws Exception {
        String methodName = "getAccessToken()";
        //尝试从缓存获取
        /*Object accessTokenObj = CacheHelper.keys(CacheKey.DINGTALK_ACCESS_TOKEN, appKey).get();
        if (accessTokenObj != null && StringUtils.isNotBlank(accessTokenObj.toString())) {
            return accessTokenObj.toString();
        }*/
        //缓存不存在调用接口获取
        DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GET_CORP_TOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse oapiGettokenResponse = client.execute(request);
        log.info("{},oapiGettokenResponse:{}", methodName, JSON.toJSONString(oapiGettokenResponse));
        if (oapiGettokenResponse == null || !DingTalkConstant.SUCCESS_CODE.equals(oapiGettokenResponse.getErrorCode()) || StringUtils.isBlank(oapiGettokenResponse.getAccessToken())) {
            throw new Exception("getAccessToken error");
        }
        //设置缓存
        // CacheHelper.keys(CacheKey.DINGTALK_ACCESS_TOKEN, appKey).set(oapiGettokenResponse.getAccessToken(), CacheKey.DINGTALK_ACCESS_TOKEN.getExpireIn());
        return oapiGettokenResponse.getAccessToken();
    }

    /**
     * 获取token
     *
     * @return 获取access_token
     * @throws Exception
     */
    /*public String getCorpAccessToken() throws Exception {
        String methodName = "getCorpAccessToken()";
        //尝试从缓存获取
        Object accessTokenObj = CacheHelper.keys(CacheKey.DINGTALK_ACCESS_TOKEN_CORP, appKey).get();
        if (accessTokenObj != null && StringUtils.isNotBlank(accessTokenObj.toString())) {
            return accessTokenObj.toString();
        }
        //缓存不存在调用接口获取
        com.aliyun.dingtalkoauth2_1_0.Client client = createClient();
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey(appKey)
                .setAppSecret(appSecret);
        GetAccessTokenResponse accessToken = client.getAccessToken(getAccessTokenRequest);
        log.info("{},accessToken:{}", methodName, JSON.toJSONString(accessToken));
        //设置缓存
        CacheHelper.keys(CacheKey.DINGTALK_ACCESS_TOKEN_CORP, appKey).set(accessToken.getBody().getAccessToken(), accessToken.getBody().expireIn);
        return accessToken.getBody().getAccessToken();
    }*/

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    /*public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }*/
    public static void main(String[] args) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_GET_CORP_TOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("");
        request.setAppsecret("");
        request.setHttpMethod("GET");
        OapiGettokenResponse oapiGettokenResponse = client.execute(request);
        log.info("{},oapiGettokenResponse:{}", JSON.toJSONString(oapiGettokenResponse));
    }


}
