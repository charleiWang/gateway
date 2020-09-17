//package com.idaas.gateway.config.test.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
//    @Override
//    public GatewayFilter apply(Object config) {
//
//        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                List<String> accountInfo = exchange.getResponse().getHeaders().get("AccountInfo");
//                exchange.getResponse().getHeaders().remove("AccountInfo");//移除包头中的用户信息不需要返回给客户端
//                log.info("返回的accountInfo：{}，", accountInfo);
//                if(accountInfo != null && accountInfo.size() > 0) {
//                    String gmAccountInfoJson = accountInfo.get(0);//获取header中的用户信息
//                    //将信息放在session中，在后面认证的请求中使用
//                    exchange.getSession().block().getAttributes().put("AccountInfo", gmAccountInfoJson);
//                }
//            log.info("登陆返回信息:{}",accountInfo);
//        }));
//    }
//}
