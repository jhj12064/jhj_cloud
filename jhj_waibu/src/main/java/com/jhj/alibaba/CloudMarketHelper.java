package com.jhj.alibaba;

import com.jhj.utils.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云市场请求助手
 */
public class CloudMarketHelper {

    public enum HeaderContentType{

        FROM("application/x-www-form-urlencoded; charset=UTF-8"),

        JSON("application/json; charset=UTF-8");

        private String type;

        public String getType(){
            return this.type;
        }
        HeaderContentType(String type){
            this.type = type;
        }
    }


    private String appCode;

    private String host;

    private String path;

    private HeaderContentType contentType;

    public String doPost(Map<String, String> querys, Map<String,String> bodys) throws Exception {
        return rp2Str(HttpUtil.doPost(host, path, excuteHeader(), querys, bodys));
    }

    public String doPost(Map<String, String> querys,String bodys) throws Exception{
        return rp2Str(HttpUtil.doPost(host, path, excuteHeader(), querys, bodys));
    }

    private Map<String, String> excuteHeader(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appCode);
        headers.put("Content-Type", contentType.getType());
        return headers;
    }

    private String rp2Str(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    public CloudMarketHelper(String appCode,String host,String path,HeaderContentType contentType){
        this.appCode = appCode;
        this.host = host;
        this.path = path;
        this.contentType = contentType;

    }


}
