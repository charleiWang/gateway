//package com.idaas.gateway.config.test.security;
//
//import com.alibaba.fastjson.JSONObject;
//import com.idaas.gateway.config.test.cas.CasAuthenticationRequestMatcher;
//import com.idaas.gateway.config.test.cas.CasConfig;
//import com.idaas.gateway.config.test.cas.PrimusCasAuthenticationEntryPoint;
//import com.idaas.gateway.config.test.cas.PrimusCasAuthenticationFilter;
//import io.netty.buffer.UnpooledByteBufAllocator;
//import lombok.extern.slf4j.Slf4j;
//import org.jasig.cas.client.session.SingleSignOutFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.NettyDataBufferFactory;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
//import org.springframework.web.server.WebFilter;
//import reactor.core.publisher.Flux;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Iterator;
//
///**
// * description: Security 权限拦截
// * 注意:cloud gateway 不能和spring-web混合使用，cloud gateway采用的webflux技术，不能再引入spring-web包。
// * 创建security的核心安全配置类SecurityConfig并自定义SecurityWebFilterChain，
// * 在webflux环境下要生效必须用注解@EnableWebFluxSecurity使其生效
// *
// * @author bang.tang
// * @date 2020/9/15--11:33
// */
//@Slf4j
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private CasConfig casConfig;
//
//
//    //security的鉴权排除的url列表
//    private static final String[] excludedAuthPages = {
//            "/soa-auth/account/index",
//            "/soa-auth/account/login",
//            "/soa-auth/account/logout",
//            "/soa-auth/account/logout",
//            "/soa-auth/account/logout",
//            "/health",
//            "/login",
//            "/soa-auth/easyui/**",
//            "/soa-auth/js/**"
//    };
//    //security的鉴权排除的url列表
//    private static final String LOGIN_PAGE = "/soa-auth/account/index";
//    private static final String LOGOUT_PAGE = "/soa-auth/account/logout";
////    private static final String LOGIN_PAGE_AUTHEN = "/soa-auth/account/authen";
//    private static final String LOGIN_PAGE_AUTHEN = "/soa-auth/main";
//
//
//
//    @Bean
//    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
//        RedirectServerAuthenticationEntryPoint loginPoint = new RedirectServerAuthenticationEntryPoint(casAuthenticationEntryPoint().getLoginUrl());
//        http.authorizeExchange()
////            .pathMatchers("/static-resource/**", basePath1).permitAll()
//                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
//                .anyExchange().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin() //启动页面表单登陆,spring security 内置了一个登陆页面/login
////                .loginPage(LOGIN_PAGE_AUTHEN)
////                .authenticationEntryPoint(casAuthenticationEntryPoint())
////                .authenticationSuccessHandler((webFilterExchange, authentication) -> {//认证成功之后返回给客户端的信息
////                    log.info("认证成功回调!");
////                    JSONObject result = new JSONObject();
////                    result.put("code", 0);
////                    return webFilterExchange.getExchange().getResponse().writeWith(Flux.create(sink -> {
////                        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
////                        try {
////                            DataBuffer dataBuffer = nettyDataBufferFactory.wrap(result.toJSONString().getBytes("utf8"));
////                            sink.next(dataBuffer);
////                        } catch (UnsupportedEncodingException e) {
////                            e.printStackTrace();
////                        }
////                        sink.complete();
////                    }));
////                })
////                .and().authorizeExchange().pathMatchers("/soa-auth/account/main").access(new XinyueReactiveAuthorizationManager("Manager", "Dev"))
//                .and().csrf().disable()//必须支持跨域
////                .logout().logoutUrl(LOGOUT_PAGE)
//        ;
//        http.exceptionHandling()
//                .authenticationEntryPoint(casAuthenticationEntryPoint());
//
//        return http.build();
////        SecurityWebFilterChain chain = http.build();
////        Iterator<WebFilter> weIterable = chain.getWebFilters().toIterable().iterator();
////        while (weIterable.hasNext()) {
////            WebFilter f = weIterable.next();
////            if (f instanceof AuthenticationWebFilter) {
////                AuthenticationWebFilter webFilter = (AuthenticationWebFilter) f;
////                //将自定义的AuthenticationConverter添加到过滤器中
////                webFilter.setServerAuthenticationConverter(new XinyueAuthenticationConverter());
////            }
////        }
////        return chain;
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setDefaultTargetUrl(serviceProperties().getService());
//        return successHandler;
//    }
//
//    @Bean
//    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
//        return new ReactiveAuthenticationManagerAdapter((authentication) -> {
//            if (authentication instanceof XinyueAccountAuthentication) {
//                XinyueAccountAuthentication gmAccountAuthentication = (XinyueAccountAuthentication) authentication;
//                if (gmAccountAuthentication.getPrincipal() != null) {
//                    log.info("登录的principal消息:{} ", gmAccountAuthentication.getPrincipal());
//                    authentication.setAuthenticated(true);
//                    return authentication;
//                } else {
//                    return authentication;
//                }
//            } else {
//                return authentication;
//            }
//        });
//    }
//
//    /**
//     * 认证的入口
//     */
//    @Bean
//    public PrimusCasAuthenticationEntryPoint casAuthenticationEntryPoint() {
//        PrimusCasAuthenticationEntryPoint entryPoint = new PrimusCasAuthenticationEntryPoint(casConfig.getCasServerLoginUrl(), serviceProperties());
//        log.info("认证的入口 entryPoint:{}", entryPoint.getLoginUrl());
//        log.info("认证的入口 serviceProperties:{}", entryPoint.getServiceProperties());
//        return entryPoint;
//    }
//
//    /**
//     * 指定service相关信息
//     */
//    @Bean
//    public ServiceProperties serviceProperties() {
//        ServiceProperties serviceProperties = new ServiceProperties();
//        serviceProperties.setService(casConfig.getAppServerUrl() + casConfig.getAppLoginUrl());
//        serviceProperties.setAuthenticateAllArtifacts(true);
//        log.info("指定service相关信息 Service:{}", serviceProperties.getService());
//        log.info("指定service相关信息 AuthenticateAllArtifacts:{}", serviceProperties.getArtifactParameter());
//        return serviceProperties;
//    }
//
//    /**
//     * 单点登出过滤器
//     */
////    @Bean
//    public SingleSignOutFilter singleSignOutFilter() {
//        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
////        singleSignOutFilter.setCasServerUrlPrefix(casConfig.getCasServerUrl());
//        singleSignOutFilter.setIgnoreInitConfiguration(true);
//        return singleSignOutFilter;
//    }
//
//    /**
//     * 请求单点退出过滤器
//     */
////    @Bean
//    public LogoutFilter casLogoutFilter() {
//        LogoutFilter logoutFilter = new LogoutFilter(casConfig.getCasServerLogoutUrl(), new SecurityContextLogoutHandler());
//        logoutFilter.setFilterProcessesUrl(casConfig.getAppLogoutUrl());
//        return logoutFilter;
//    }
//
//    /**
//     * CAS认证过滤器
//     */
////    @Bean
//    public PrimusCasAuthenticationFilter casAuthenticationFilter() throws Exception {
//        PrimusCasAuthenticationFilter casAuthenticationFilter = new PrimusCasAuthenticationFilter();
//        casAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new CasAuthenticationRequestMatcher());
////        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        casAuthenticationFilter.setFilterProcessesUrl(casConfig.getAppLoginUrl());
//        return casAuthenticationFilter;
//    }
//
//
//
//}
