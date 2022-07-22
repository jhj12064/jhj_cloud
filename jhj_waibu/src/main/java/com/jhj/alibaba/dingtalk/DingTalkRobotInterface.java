package com.jhj.alibaba.dingtalk;

import com.jhj.alibaba.dingtalk.enums.RobotGroupMsgType;
import com.jhj.alibaba.dingtalk.model.bo.RobotTextMsgBodyBO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotGroupMsgDTO;
import com.jhj.alibaba.dingtalk.model.dto.DingTalkRobotSampleMsgDTO;
import com.jhj.alibaba.dingtalk.service.RobotService;
import com.jhj.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * 钉钉机器人
 *
 */
@Slf4j
@Component
public class DingTalkRobotInterface {

    @Autowired
    DingTalkTokenInterface dingTalkTokenInterface;

    @Value("${aliyun.dingTalk.robot.appSecret}")
    private String appSecret;


    /**
     * 钉钉机器人发送群聊消息
     *
     * @param t
     * @param webhook 地址
     * @param isSign  是否需要加签
     * @param secret  加签秘钥 , 需要加签时必填
     */
    public void sendGroupChat(DingTalkRobotGroupMsgDTO t, String webhook, boolean isSign, String secret) {
        RobotService service = SpringContextUtils.getBean(t.getMsgtype().getServiceName(), RobotService.class);
        service.sendGroupChat(t, webhook, isSign, secret);
    }

    /**
     * 钉钉机器人发送单聊消息
     *
     * @param t
     */
    public void sendSampleMsg(DingTalkRobotSampleMsgDTO t) {
        RobotService service = SpringContextUtils.getBean(t.getMsgtype().getServiceName(), RobotService.class);
        service.sendSampleMsg(t);
    }


    /**
     * @param t
     * @ 机器人后接受消息
     */
    public void receiveRoboMsg(DingTalkRobotDTO t) {
        // TODO 具体业务
        String sessionWebhook = t.getSessionWebhook();
        DingTalkRobotDTO.Text text = t.getText();
        String content = text.getContent();
        // for example 发送一条text群聊消息
        DingTalkRobotGroupMsgDTO.DingTalkRobotGroupMsgDTOBuilder builder = DingTalkRobotGroupMsgDTO
                .builder().msgtype(RobotGroupMsgType.text)
                .text(new RobotTextMsgBodyBO(content))
                .at(new DingTalkRobotGroupMsgDTO.At());
        this.sendGroupChat(builder.build(), sessionWebhook, false, null);
    }

    /**
     * 校验消息是否合法
     *
     * @param dingTimestamp
     * @param dingSign
     * @return
     */
    public boolean checkSign(Long dingTimestamp, String dingSign) {
        try {
            //获取当前时间的时间戳
            Long currentTimeMillis = System.currentTimeMillis();
            //比较两者差值
            Long time = currentTimeMillis - dingTimestamp;
            //使用请求头header中钉钉发送的timestamp时间戳和开发者后台机器人应用的AppSecret，进行加密操作
            String stringToSign = dingTimestamp + "\n" + appSecret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            //获取加密sign
            String sign = new String(Base64.encodeBase64(signData));
            //相差时间在1小时内且sign同时验证通过，才能认为是来自钉钉的合法请求
            if (time < 3600000 && sign.equals(dingSign)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 卡片互动消息
     *
     * @throws Exception
     */
    /*public void card(DingTalkRobotCardDTO t) throws Exception {
        String accessToken = dingTalkTokenInterface.getCorpAccessToken();
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        com.aliyun.dingtalkim_1_0.Client client = new com.aliyun.dingtalkim_1_0.Client(config);

        SendInteractiveCardHeaders sendInteractiveCardHeaders = new SendInteractiveCardHeaders();
        sendInteractiveCardHeaders.xAcsDingtalkAccessToken = accessToken;
        SendInteractiveCardRequest.SendInteractiveCardRequestCardOptions cardOptions = new SendInteractiveCardRequest.SendInteractiveCardRequestCardOptions()
                .setSupportForward(t.getSupportForward());
        Map<String, String> atOpenIds = t.getAtOpenIds();
        //
        Map<String, DingTalkRobotCardDTO.CardData> privateData1 = t.getPrivateData();
        List<TeaPair> arrayList = new ArrayList<>();
        for (Map.Entry<String, DingTalkRobotCardDTO.CardData> e : privateData1.entrySet()) {
            DingTalkRobotCardDTO.CardData value = e.getValue();
            Map<String, String> privateDataValueKeyCardMediaIdParamMap = TeaConverter.buildMap(
                    value.getCardMediaIdParamMap().toArray(new TeaPair[value.getCardMediaIdParamMap().size()])

            );
            Map<String, String> privateDataValueKeyCardParamMap = TeaConverter.buildMap(
                    value.getCardParamMap().toArray(new TeaPair[value.getCardParamMap().size()])

            );
            PrivateDataValue privateDataValueKey = new PrivateDataValue()
                    .setCardParamMap(privateDataValueKeyCardParamMap)
                    .setCardMediaIdParamMap(privateDataValueKeyCardMediaIdParamMap);
            arrayList.add(new TeaPair(e.getKey(), privateDataValueKey));
        }
        java.util.Map<String, PrivateDataValue> privateData = TeaConverter.buildMap(
                arrayList.toArray(new TeaPair[arrayList.size()])
        );
        DingTalkRobotCardDTO.CardData cardData1 = t.getCardData();
        java.util.Map<String, String> cardDataCardMediaIdParamMap = TeaConverter.buildMap(
                cardData1.getCardMediaIdParamMap().toArray(new TeaPair[cardData1.getCardMediaIdParamMap().size()])
        );
        java.util.Map<String, String> cardDataCardParamMap = TeaConverter.buildMap(
                cardData1.getCardParamMap().toArray(new TeaPair[cardData1.getCardParamMap().size()])
        );
        SendInteractiveCardRequest.SendInteractiveCardRequestCardData cardData = new SendInteractiveCardRequest.SendInteractiveCardRequestCardData()
                .setCardParamMap(cardDataCardParamMap)
                .setCardMediaIdParamMap(cardDataCardMediaIdParamMap);
        SendInteractiveCardRequest sendInteractiveCardRequest = new SendInteractiveCardRequest()
                .setCardTemplateId(t.getCardTemplateId())
                .setOpenConversationId(t.getOpenConversationId())
                .setReceiverUserIdList(t.getReceiverUserIdList())
                .setOutTrackId(t.getOutTrackId())
                .setRobotCode(t.getRobotCode())
                .setConversationType(t.getConversationType())
                .setCallbackRouteKey(t.getCallbackRouteKey())
                .setCardData(cardData)
                .setPrivateData(privateData)
                .setChatBotId(t.getChatBotId())
                .setUserIdType(t.getUserIdType())
                .setAtOpenIds(atOpenIds)
                .setCardOptions(cardOptions);
        try {
            client.sendInteractiveCardWithOptions(sendInteractiveCardRequest, sendInteractiveCardHeaders, new RuntimeOptions());
        } catch (Exception e) {
            log.error("error", e);
            throw new GlobalException(Error.DING_TALK_ROBOT_ERROR);
        }
    }*/


}
