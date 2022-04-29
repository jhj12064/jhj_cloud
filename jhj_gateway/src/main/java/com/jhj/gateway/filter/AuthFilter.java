//package com.jhj.gateway.filter;
//
//import com.jhj.common.Contants;
//import com.jhj.common.auth.JwtUtil;
//import com.jhj.common.model.Result;
//import com.jhj.common.enums.Error;
//import com.jhj.gateway.config.ExclusionUrl;
//import com.jhj.gateway.config.WhiteList;
//import com.jhj.utils.AddressUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.io.Serializable;
//import java.util.List;
//
//@Component
//@Slf4j
//@RefreshScope
//public class AuthFilter implements GlobalFilter, Ordered {
//
//    @Autowired
//    private ExclusionUrl exclusionUrl;
//
//    @Autowired
//    private WhiteList whiteList;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        String ip = AddressUtils.getIP(request);
//        log.info("request ip................:{}", ip);
//        if(isWhiteIp(ip)){
//            return chain.filter(exchange);
//        }
//        String path = request.getURI().getPath();
//        log.info("request path................:{}", path);
//        if (isExclusionUrl(path)) {
//            return chain.filter(exchange);
//        }
//        String token = request.getHeaders().getFirst(Contants.USER_FROM_TOKENKEY);
//        log.info("request token...............:{}", token);
//        if (StringUtils.isEmpty(token)) {
//            log.error("request token is empty...............:{}", token);
//            return getVoidMono(response, Error.SC_UNAUTHORIZED_TOKEN_EXPIRED);
//        }
//        if (!verifierToken(token)) {
//            log.error("request token is invalid...............:{}", token);
//            return getVoidMono(response, Error.SC_UNAUTHORIZED_TOKEN_EXPIRED);
//        }
//        return chain.filter(exchange);
//    }
//
//
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//
//    private Mono<Void> getVoidMono(ServerHttpResponse response, Error error) {
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        response.setStatusCode(HttpStatus.OK);
//        byte[] bits = Result.getInstance().fail(error).toJSONString().getBytes();
//        DataBuffer buffer = response.bufferFactory().wrap(bits);
//        return response.writeWith(Mono.just(buffer));
//    }
//
//
//    private boolean isExclusionUrl(String path) {
//        if(null != exclusionUrl){
//            List<String> exclusions = exclusionUrl.getUrl();
//            if (!CollectionUtils.isEmpty(exclusions)) {
//                return exclusions.stream().anyMatch(action ->  CommonAntPathMatcher.getInstance().match(action, path));
//            }
//        }
//        return false;
//    }
//
//    private boolean isWhiteIp(String ip) {
//        if(null != whiteList){
//            List<String> ips = whiteList.getIp();
//            if (!CollectionUtils.isEmpty(ips)) {
//                return ips.stream().anyMatch(action ->  CommonAntPathMatcher.getInstance().match(action, ip));
//            }
//        }
//        return false;
//    }
//
//    static class CommonAntPathMatcher extends AntPathMatcher implements Serializable,Cloneable{
//        private static final long serialVersionUID = -6902126758299604L;
//        private static CommonAntPathMatcher antPathMatcher = new CommonAntPathMatcher();
//        public static CommonAntPathMatcher getInstance(){
//            try {
//                return (CommonAntPathMatcher) antPathMatcher.clone();
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//            return new CommonAntPathMatcher();
//        }
//    }
//
//    private boolean verifierToken(String token) {
//        return JwtUtil.verify(token);
//    }
//
//
//}
