package com.jhj.common.Intercptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Configuration
public class FeignInterceptor implements RequestInterceptor {


    private HttpServletRequest getServletRequest() {
        try {
            // 这种方式获取的HttpServletRequest是线程安全的
            HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            if(null == request){
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            }
            return request;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = getServletRequest();
        if(null != request){
            // 对消息头进行配置
            Enumeration<String> attributeNames = request.getHeaderNames();
            if (attributeNames != null) {
                while (attributeNames.hasMoreElements()) {
                    String name = attributeNames.nextElement();
                    String value = request.getHeader(name);
                    template.header(name,value);
                }
            }
            // 对请求体进行配置
            Enumeration<String> bodyNames = request.getParameterNames();
            StringBuffer body =new StringBuffer();
            if (bodyNames != null) {
                while (bodyNames.hasMoreElements()) {
                    String name = bodyNames.nextElement();
                    String values = request.getParameter(name);
                    body.append(name).append("=").append(values).append("&");
                }
            }
            if(body.length()!=0) {
                body.deleteCharAt(body.length()-1);
                template.body(body.toString());
            }
        }

    }

}