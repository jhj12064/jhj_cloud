package com.jhj.alibaba.dingtalk.service.impl;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.google.common.collect.Lists;
import com.jhj.alibaba.dingtalk.DingTalkTokenInterface;
import com.jhj.alibaba.dingtalk.constant.DingTalkConstant;
import com.jhj.alibaba.dingtalk.enums.WorkNoticeTargetType;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkWorkNoticePushDTO;
import com.jhj.alibaba.dingtalk.service.WorkNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jhj.alibaba.dingtalk.constant.DingTalkConstant.CLIENT_URL_SEND_WORK_NOTICE;

/**
 * @author Jeremy
 * @date 2022/2/23 16:35
 */
@Slf4j
public abstract class AbsWorkNotice implements WorkNoticeService {

    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;

    @Value("${aliyun.dingTalk.agentId}")
    private String agentId;

    public void send(DingTalkWorkNoticePushDTO t) {
        try {
            log.info("{},dingTalkWorkNoticePushDTO:{}", "sendWorkNotice()", JSON.toJSONString(t));
            String accessToken = dingTalkTokenInterface.getAccessToken();
            DingTalkClient client = new DefaultDingTalkClient(CLIENT_URL_SEND_WORK_NOTICE);
            List<OapiMessageCorpconversationAsyncsendV2Request> requestList = this.getRequestList(t);
            //发送目标过多，需要循环发送
            for (OapiMessageCorpconversationAsyncsendV2Request request : requestList) {
                OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, accessToken);
                log.info("{},rsp:{}", "sendWorkNotice()", JSON.toJSONString(rsp));
                if (rsp == null || !DingTalkConstant.SUCCESS_CODE.equals(rsp.getErrorCode())) {
                    //throw new GlobalException(Error.DING_TALK_WORK_NOTICE_ERROR);
                }
            }
        } catch (Exception e) {
            log.error("sendWorkNotice_error", e);
            // throw new GlobalException(Error.DING_TALK_WORK_NOTICE_ERROR);
        }
    }

    /**
     * 获取Msg
     *
     * @param t
     * @return
     */
    protected abstract OapiMessageCorpconversationAsyncsendV2Request.Msg getMsg(DingTalkWorkNoticePushDTO t);

    /**
     * 获取request
     *
     * @return
     */
    private List<OapiMessageCorpconversationAsyncsendV2Request> getRequestList(DingTalkWorkNoticePushDTO t) {
        List<OapiMessageCorpconversationAsyncsendV2Request> result = new ArrayList<>();
        //发送给用户，每次最多100条
        if (WorkNoticeTargetType.USER.equals(t.getTargetType())) {
            List<String> userIdList = t.getUserIdList();
            if (CollectionUtils.isEmpty(userIdList)) {
                return result;
            }
            List<List<String>> partition = Lists.partition(userIdList, 100);
            for (List<String> userIdListPartition : partition) {
                OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
                request.setAgentId(Long.valueOf(agentId));
                request.setToAllUser(false);
                request.setUseridList(this.transformToComma(userIdListPartition));
                request.setMsg(this.getMsg(t));
                result.add(request);
            }
        } else {
            //发送给部门，每次最多20条
            List<String> deptIdList = t.getDeptIdList();
            if (CollectionUtils.isEmpty(deptIdList)) {
                return result;
            }
            List<List<String>> partition = Lists.partition(deptIdList, 20);
            for (List<String> deptIdListPartition : partition) {
                OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
                request.setAgentId(Long.valueOf(agentId));
                request.setToAllUser(false);
                request.setDeptIdList(this.transformToComma(deptIdListPartition));
                request.setMsg(this.getMsg(t));
                result.add(request);
            }
        }
        return result;
    }

    /**
     * 转换逗号隔开的字符串
     *
     * @param strList
     * @return
     */
    private String transformToComma(List<String> strList) {
        if (CollectionUtils.isEmpty(strList)) {
            return null;
        }
        return strList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }


}
