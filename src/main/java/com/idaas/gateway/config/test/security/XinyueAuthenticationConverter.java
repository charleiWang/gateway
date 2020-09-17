//package com.idaas.gateway.config.test.security;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebSession;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//@Slf4j
//public class XinyueAuthenticationConverter extends ServerFormLoginAuthenticationConverter {
//    @Override
//    public Mono<Authentication> convert(ServerWebExchange exchange) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        //从session中获取登陆用户信息
//        Mono<WebSession> session = exchange.getSession();
//        log.info("返回的 session :{}", session);
//
//        String value = "{\"roles\":[\"Admin\",\"Dev\"],\"username\":\"xinyues\"}";
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 10 * 1000L, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
//        Future<Object> accountInfo = poolExecutor.submit(new Callable<Object>() {
//
//            @Override
//            public Object call() throws Exception {
//                String accountInfo = exchange.getSession().block().getAttribute("AccountInfo").toString();
//                log.info("线程池获取accountInfo：{}", accountInfo);
//                return accountInfo;
//            }
//        });
//        try {
//            value = accountInfo.get().toString();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        if (StringUtils.isBlank(value)) {
//            log.error("用户认证信息为空,返回获取认证信息失败");
//            //添加用户信息到spring security之中。
//            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//
//            XinyueAccountAuthentication xinyueAccountAuthentication = new XinyueAccountAuthentication(null, value, simpleGrantedAuthorities);
//            return Mono.just(xinyueAccountAuthentication);
////            return Mono.empty();
//        } else {
//            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//            //获取权限信息
//            List<String> roels = JSON.parseObject(value).getJSONArray("roles").toJavaList(String.class);
//            roels.forEach(role -> {
//                //这里必须添加前缀，参考：AuthorityReactiveAuthorizationManager.hasRole(role)
//                SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + role);
//                simpleGrantedAuthorities.add(auth);
//            });
//            //添加用户信息到spring security之中。
//            XinyueAccountAuthentication xinyueAccountAuthentication = new XinyueAccountAuthentication(null, value, simpleGrantedAuthorities);
//            return Mono.just(xinyueAccountAuthentication);
//        }
//    }
//}
