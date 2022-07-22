package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.common.collect.Lists;
import com.jhj.alibaba.dingtalk.DingTalkTokenInterface;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import com.jhj.alibaba.dingtalk.service.RobotService;
import com.jhj.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Jeremy
 * @date 2022/2/23 14:35
 */
@Slf4j
public abstract class AbsRobot implements RobotService {

    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;

    /**
     * 群聊消息
     *
     * @param t
     * @param webhook
     * @param isSign
     * @param secret
     */
    protected void groupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        try {
            log.info("{},t:{},webhook:{},isSign:{},secret:{}", "AbsRobot_send()", JSON.toJSONString(t), webhook, isSign, secret);
            DingTalkClient client = new DefaultDingTalkClient(this.getServerUrl(webhook, isSign, secret));
            OapiRobotSendRequest request = this.buildRobotGroupSendRequest(t);
            OapiRobotSendResponse rsp = client.execute(request);
            log.info("{},rsp:{}", "AbsRobot_send()", JSON.toJSONString(rsp));
            if (rsp == null || !DingTalkConstant.SUCCESS_CODE.equals(rsp.getErrorCode())) {
                throw new GlobalException(null);
            }
        } catch (Exception e) {
            log.error("AbsRobot_send_error", e);
            throw new GlobalException(null);
        }
    }

    /**
     * 构建群聊参数
     *
     * @param t
     * @return
     */
    protected abstract OapiRobotSendRequest buildRobotGroupSendRequest(DingTalkRobotGroupMsgDTO t);

    /**
     * 单聊消息
     *
     * @param t
     */
    protected void sampleMsg(DingTalkRobotSampleMsgDTO t) {
        try {
            new com.aliyun.dingtalkalgo_1_0.models.NlpWordDistinguishRequest();
            log.info("{},t:{},", "sampleMsg()", JSON.toJSONString(t));
            Config config = new Config();
            config.protocol = "https";
            config.regionId = "central";
            com.aliyun.dingtalkrobot_1_0.Client client = new com.aliyun.dingtalkrobot_1_0.Client(config);
            BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
            batchSendOTOHeaders.xAcsDingtalkAccessToken = dingTalkTokenInterface.getAccessToken();
            //分批发送，一次最多发送20个人
            List<List<String>> partition = Lists.partition(t.getUserIds(), 20);
            for (List<String> userIds : partition) {
                BatchSendOTORequest batchSendOTORequest = this.buildBatchSendOTORequest(t, userIds);
                BatchSendOTOResponse rsp = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
                log.info("{},rsp:{}", "sampleMsg()", JSON.toJSONString(rsp));
            }
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                log.error("sampleMsg_error, code:{}, message:{}", err.code, err.message);
                throw new GlobalException(null);
            }
        } catch (Exception e) {
            log.error("sampleMsg_error", e);
            throw new GlobalException(null);
        }
    }

    /**
     * 构建单聊参数
     *
     * @param t
     * @return
     */
    protected abstract BatchSendOTORequest buildBatchSendOTORequest(DingTalkRobotSampleMsgDTO t, List<String> userIds);


    /**
     * 获取 url
     *
     * @param webhook 地址
     * @param isSign  是否加签
     * @param secret  秘钥
     * @return
     * @throws Exception
     */
    private String getServerUrl(String webhook, boolean isSign, String secret) throws Exception {
        StringBuilder serverUrl = new StringBuilder();
        serverUrl.append(webhook);
        if (isSign) {
            Long timestamp = System.currentTimeMillis();
            serverUrl.append("&timestamp=").append(timestamp).append("&sign=").append(this.sign(timestamp, secret));
        }
        return serverUrl.toString();
    }

    /**
     * 获取签名
     *
     * @param timestamp
     * @return
     * @throws Exception
     */
    private String sign(Long timestamp, String secret) throws Exception {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }
}
