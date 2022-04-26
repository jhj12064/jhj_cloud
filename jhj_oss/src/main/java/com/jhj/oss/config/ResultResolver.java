package com.jhj.oss.config;

import com.jhj.common.annotation.FeignBody;
import com.jhj.common.model.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.Serializable;

@RestControllerAdvice(basePackages = {"com.jhj.oss.controller"})
public class ResultResolver implements ResponseBodyAdvice<Object>, Serializable {
    private static final long serialVersionUID = -4891503469388315699L;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result || body instanceof String || returnType.getExecutable().isAnnotationPresent(FeignBody.class)) {
            return body;
        }
        return Result.getInstance().success(body);
    }

}
