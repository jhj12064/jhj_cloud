package com.jhj.oss.interceptor;

import com.jhj.common.Contants;
import com.jhj.common.auth.JwtUtil;
import com.jhj.common.model.Result;
import com.jhj.common.enums.Error;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author Jeremy
 */
@Slf4j
public class RequestInterceptor implements AsyncHandlerInterceptor {

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader(Contants.USER_FROM_TOKENKEY);

        if(StringUtils.isEmpty(token)){
            log.error("request token is empty...............:{}", token);
            response(response, Error.SC_UNAUTHORIZED_TOKEN_EXPIRED);
            return false;
        }
        if(!verifierToken(token)){
            log.error("request token is invalid...............:{}", token);
            response(response, Error.SC_UNAUTHORIZED_TOKEN_EXPIRED);
            return false;
        }
        return true;
    }

    private boolean verifierToken(String token){
        return JwtUtil.verify(token);
    }

    private  void response( HttpServletResponse response, Error error) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(Result.getInstance().fail(error).toJSONString());
    }
}
