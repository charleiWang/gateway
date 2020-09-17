//package com.idaas.gateway.config.test.security;
//
//import com.alibaba.fastjson.JSONObject;
//import com.idaas.gateway.model.JsonResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import reactor.core.publisher.Mono;
//import sun.security.pkcs11.wrapper.Constants;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * description: 登录成功处理
// *
// * @author bang.tang
// * @date 2020/9/17--8:54
// */
//@Slf4j
//public class JsonServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
//    @Override
//    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
//        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
//        response.setStatusCode(HttpStatus.OK);
//        JsonResult result = JsonResult.success("成功");
//
//
//        String body = JSONObject.toJSONString(result);
//        DataBuffer buffer = null;
//        try {
//            buffer = response.bufferFactory().wrap(body.getBytes("utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            log.error("代码编码错误:{}",e);
//        }
//        return response.writeWith(Mono.just(buffer));
//
//    }
//}
