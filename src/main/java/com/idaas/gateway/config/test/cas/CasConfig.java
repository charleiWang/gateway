//package com.idaas.gateway.config.test.cas;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.util.Assert;
//
//import javax.annotation.Resource;
//
///**
// * Created by younger on 17/11/3.
// * CAS的配置参数
// */
//@Configuration
//public class CasConfig {
//    @Resource
//    private Environment environment;
//
//    public String getPlatformType() {
//        String platformType = environment.getProperty("primus.application.platform-type");
//        Assert.hasText(platformType, "未定义当前平台的类型: primus.application.platform-type");
//        return platformType;
//    }
//
//    public String getPlatformTypeName() {
//        String platformTypeName = environment.getProperty("primus.application.platform-type-name");
//        Assert.hasText(platformTypeName, "未定义当前平台的类型: primus.application.platform-type-name");
//        return platformTypeName;
//    }
//
//    /**
//     * 当前应用的类型
//     */
//    public String getApplicationType() {
//
//        String currentApplicationType = environment.getProperty("primus.application.type");
//        if (StringUtils.isBlank(currentApplicationType)) {
//            currentApplicationType = environment.getProperty("spring.application.name");
//        }
//        Assert.hasText(currentApplicationType, "未定义当前应用的类型: primus.application.type");
//        return currentApplicationType;
//    }
//
//    /**
//     * 当前应用的访问地址
//     */
//    public String getApplicationHost() {
//
//        String currentApplicationType = environment.getProperty("primus.application.host");
//        Assert.hasText(currentApplicationType, "未定义当前应用的类型: primus.application.host");
//        return currentApplicationType;
//    }
//
//    /**
//     * CasServer 服务器url地址
//     */
//    public String getCasServerUrl() {
//
//        String casServerHost = environment.getProperty("primus.cas.server.host");
//        Assert.hasText(casServerHost, "未定义cas认证地址: primus.cas.server.host");
//        return casServerHost;
//    }
//
//    /**
//     * CasServer 服务器登录url地址
//     */
//    public String getCasServerLoginUrl() {
//        return String.format("%s/login", getCasServerUrl());
//    }
//
//    /**
//     * 退出url地址
//     */
//    public String getCasServerLogoutUrl() {
//        return String.format("%s/logout?service=%s", getCasServerUrl(), getAppServerUrl());
//    }
//
//    /**
//     * 应用地址
//     */
//    public String getAppServerUrl() {
//
//        String currentApplicationHost = environment.getProperty("primus.application.host");
//        Assert.hasText(currentApplicationHost, "未定义当前应用的地址: primus.application.host");
//        return currentApplicationHost;
//    }
//
//    /**
//     * 应用登陆地址
//     */
//    public String getAppLoginUrl() {
////        return String.format("/swagger-ui.html");
//        return String.format("/account/main");
//    }
//
//    /**
//     * 应用退出地址
//     */
//    public String getAppLogoutUrl() {
//        return String.format("/logout", getAppServerUrl());
//    }
//}
