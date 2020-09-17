//package com.idaas.gateway.config.test.cas;
//
//import org.jasig.cas.client.util.CommonUtils;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
//import org.springframework.util.Assert;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by younger on 17/11/2.
// * extends CasAuthenticationEntryPoint
// * 统一登录平台入口类
// */
//public class PrimusCasAuthenticationEntryPoint extends RedirectServerAuthenticationEntryPoint  implements AuthenticationEntryPoint {
//
//    public PrimusCasAuthenticationEntryPoint(String location) {
//        super(location);
//    }
//
//    public PrimusCasAuthenticationEntryPoint(String loginUrl,ServiceProperties serviceProperties) {
//        super(loginUrl);
//        this.serviceProperties = serviceProperties;
//        this.loginUrl = loginUrl;
//    }
//
//    /**
//     * 构造方法
//     *
//     * @param loginUrl          登录url地址;
//     * @param serviceProperties 配置信息
//     */
////    public PrimusCasAuthenticationEntryPoint(String loginUrl, ServiceProperties serviceProperties) {
////        this.setLoginUrl(loginUrl);
////        this.setServiceProperties(serviceProperties);
////    }
//
//    // ~ Instance fields
//    // ================================================================================================
//    private ServiceProperties serviceProperties;
//
//    private String loginUrl;
//
//    /**
//     * Determines whether the Service URL should include the session id for the specific
//     * user. As of CAS 3.0.5, the session id will automatically be stripped. However,
//     * older versions of CAS (i.e. CAS 2), do not automatically strip the session
//     * identifier (this is a bug on the part of the older server implementations), so an
//     * option to disable the session encoding is provided for backwards compatibility.
//     *
//     * By default, encoding is enabled.
//     */
//    private boolean encodeServiceUrlWithSessionId = true;
//
//    // ~ Methods
//    // ========================================================================================================
//
//    public void afterPropertiesSet() {
//        Assert.hasLength(this.loginUrl, "loginUrl must be specified");
//        Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
//        Assert.notNull(this.serviceProperties.getService(),
//                "serviceProperties.getService() cannot be null.");
//    }
//
//    public final void commence(final HttpServletRequest servletRequest,
//                               final HttpServletResponse response,
//                               final AuthenticationException authenticationException) throws IOException {
//
//        final String urlEncodedService = createServiceUrl(servletRequest, response);
//        final String redirectUrl = createRedirectUrl(urlEncodedService);
//
//        preCommence(servletRequest, response);
//
//        response.sendRedirect(redirectUrl);
//    }
//
//    /**
//     * Constructs a new Service Url. The default implementation relies on the CAS client
//     * to do the bulk of the work.
//     * @param request the HttpServletRequest
//     * @param response the HttpServlet Response
//     * @return the constructed service url. CANNOT be NULL.
//     */
//    protected String createServiceUrl(final HttpServletRequest request,
//                                      final HttpServletResponse response) {
//        return CommonUtils.constructServiceUrl(null, response,
//                this.serviceProperties.getService(), null,
//                this.serviceProperties.getArtifactParameter(),
//                this.encodeServiceUrlWithSessionId);
//    }
//
//    /**
//     * Constructs the Url for Redirection to the CAS server. Default implementation relies
//     * on the CAS client to do the bulk of the work.
//     *
//     * @param serviceUrl the service url that should be included.
//     * @return the redirect url. CANNOT be NULL.
//     */
//    protected String createRedirectUrl(final String serviceUrl) {
//        return CommonUtils.constructRedirectUrl(this.loginUrl,
//                this.serviceProperties.getServiceParameter(), serviceUrl,
//                this.serviceProperties.isSendRenew(), false);
//    }
//
//    /**
//     * Template method for you to do your own pre-processing before the redirect occurs.
//     *
//     * @param request the HttpServletRequest
//     * @param response the HttpServletResponse
//     */
//    protected void preCommence(final HttpServletRequest request,
//                               final HttpServletResponse response) {
//
//    }
//
//    /**
//     * The enterprise-wide CAS login URL. Usually something like
//     * <code>https://www.mycompany.com/cas/login</code>.
//     *
//     * @return the enterprise-wide CAS login URL
//     */
//    public final String getLoginUrl() {
//        return this.loginUrl;
//    }
//
//    public final ServiceProperties getServiceProperties() {
//        return this.serviceProperties;
//    }
//
//    public final void setLoginUrl(final String loginUrl) {
//        this.loginUrl = loginUrl;
//    }
//
//    public final void setServiceProperties(final ServiceProperties serviceProperties) {
//        this.serviceProperties = serviceProperties;
//    }
//
//    /**
//     * Sets whether to encode the service url with the session id or not.
//     *
//     * @param encodeServiceUrlWithSessionId whether to encode the service url with the
//     * session id or not.
//     */
//    public final void setEncodeServiceUrlWithSessionId(
//            final boolean encodeServiceUrlWithSessionId) {
//        this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
//    }
//
//    /**
//     * Sets whether to encode the service url with the session id or not.
//     * @return whether to encode the service url with the session id or not.
//     *
//     */
//    protected boolean getEncodeServiceUrlWithSessionId() {
//        return this.encodeServiceUrlWithSessionId;
//    }
//
//
//
//
//
//}
