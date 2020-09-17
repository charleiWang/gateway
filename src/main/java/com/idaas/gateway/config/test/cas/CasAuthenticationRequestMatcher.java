//package com.idaas.gateway.config.test.cas;
//
//import com.google.common.collect.Lists;
//import lombok.Data;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.regex.Pattern;
//
///**
// * Created by tao on 30/08/17.
// */
//@Data
//public class CasAuthenticationRequestMatcher implements RequestMatcher {
//
//    private List<String> excludeUrls = Lists.newArrayList("/primus");
//
//    private Pattern allowedMethods = Pattern
//            .compile("^(GET|POST|HEAD|TRACE|OPTIONS)$");
//
//    @Override
//    public boolean matches(HttpServletRequest request) {
//
//        if (excludeUrls != null && excludeUrls.size() > 0) {
//            String servletPath = request.getServletPath();
//            for (String url : excludeUrls) {
//                if (servletPath.contains(url)) {
//                    return false;
//                }
//            }
//        }
//        return !allowedMethods.matcher(request.getMethod()).matches();
//    }
//
//    /**
//     * 需要排除的url列表
//     */
//
//}
