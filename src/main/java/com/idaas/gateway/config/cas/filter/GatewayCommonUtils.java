
package com.idaas.gateway.config.cas.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class GatewayCommonUtils {

    //从request中 获取参数
    public static String safeGetParameter(ServerHttpRequest request, String parameter) {
        if(request.getQueryParams().get(parameter)!=null){
            return request.getQueryParams().getFirst(parameter);
        }
        return null;
    }





    //将访问的url进行编码后返回
    public static String constructServiceUrl(ServerHttpRequest request, String serviceParameterName, String artifactParameterName, boolean encode, boolean ticketValidate) {

            /*String serverName = findMatchingServerName(request, serverNames);
            URIBuilder originalRequestUrl = new URIBuilder(request.getURI().toString(), encode);

            originalRequestUrl.setParameters(request.getURI().getQuery());
            boolean containsScheme = true;
            URIBuilder builder;
            if (!serverName.startsWith("https://") && !serverName.startsWith("http://")) {
                builder = new URIBuilder(encode);
                builder.setScheme(request.getURI().getScheme());
                builder.setHost(serverName);
                containsScheme = false;
            } else {
                builder = new URIBuilder(serverName, encode);
            }

            if (!serverNameContainsPort(containsScheme, serverName) && !requestIsOnStandardPort(request)) {
                builder.setPort(request.getURI().getPort());
            }

            builder.setEncodedPath(request.getURI().toString());
            List<String> serviceParameterNames = Arrays.asList(serviceParameterName.split(","));
            if (!serviceParameterNames.isEmpty() && !originalRequestUrl.getQueryParams().isEmpty()) {
                Iterator var12 = originalRequestUrl.getQueryParams().iterator();

                label72:
                while(true) {
                    while(true) {
                        URIBuilder.BasicNameValuePair pair;
                        String name;
                        do {
                            do {
                                if (!var12.hasNext()) {
                                    break label72;
                                }

                                pair = (URIBuilder.BasicNameValuePair)var12.next();
                                name = pair.getName();
                            } while(name.equals(artifactParameterName));
                        } while(serviceParameterNames.contains(name));

                        if (!name.contains("&") && !name.contains("=")) {
                            builder.addParameter(name, pair.getValue());
                        } else {
                            URIBuilder encodedParamBuilder = new URIBuilder();
                            encodedParamBuilder.setParameters(name);
                            Iterator var16 = encodedParamBuilder.getQueryParams().iterator();

                            while(var16.hasNext()) {
                                URIBuilder.BasicNameValuePair pair2 = (URIBuilder.BasicNameValuePair)var16.next();
                                String name2 = pair2.getName();
                                if (!name2.equals(artifactParameterName) && !serviceParameterNames.contains(name2)) {
                                    builder.addParameter(name2, pair2.getValue());
                                }
                            }
                        }
                    }
                }
            }

            String result = builder.toString();*/
            //如果是验证ticket拼接url的话
            if(ticketValidate){
                String url=request.getURI().toString();
                if(url.contains("?")){
                    int index=url.indexOf("?");
                    String urlWithoutParams=url.substring(0,index+1);
                    StringBuilder stringBuilder=new StringBuilder(urlWithoutParams);
                    MultiValueMap<String,String> params=request.getQueryParams();
                    Set<String> paramsSet=params.keySet();
                    Iterator<String> paramsIterator=paramsSet.iterator();
                    while (paramsIterator.hasNext()){
                        String key=paramsIterator.next();
                        if(!"ticket".equals(key)){
                            stringBuilder.append(key).append("=").append(params.getFirst(key)).append("&");
                        }

                    }
                    return stringBuilder.substring(0,stringBuilder.length()-1);
                }else {
                    return url;
                }

            }
            //如果不是验证ticketurl
             return encode ? URLEncoder.encode(request.getURI().toString()) : request.getURI().toString();


    }

    //获取客户端部署地址，并加入host做判断逻辑处理
    protected static String findMatchingServerName(ServerHttpRequest request, String serverName) {
        String[] serverNames = serverName.split(" ");
        if (serverNames.length != 0 && serverNames.length != 1) {
            String host = request.getHeaders().getFirst("Host");
            String xHost = request.getHeaders().getFirst("X-Forwarded-Host");
            String comparisonHost;
            if (xHost != null && host == "localhost") {
                comparisonHost = xHost;
            } else {
                comparisonHost = host;
            }

            if (comparisonHost == null) {
                return serverName;
            } else {
                String[] var6 = serverNames;
                int var7 = serverNames.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String server = var6[var8];
                    String lowerCaseServer = server.toLowerCase();
                    if (lowerCaseServer.contains(comparisonHost)) {
                        return server;
                    }
                }

                return serverNames[0];
            }
        } else {
            return serverName;
        }
    }


    //检查url时候包含端口
    private static boolean serverNameContainsPort(boolean containsScheme, String serverName) {
        if (!containsScheme && serverName.contains(":")) {
            return true;
        } else {
            int schemeIndex = serverName.indexOf(":");
            int portIndex = serverName.lastIndexOf(":");
            return schemeIndex != portIndex;
        }
    }


    public static String constructRedirectUrl(String casServerLoginUrl, String serviceParameterName, String serviceUrl) {
        return casServerLoginUrl + (casServerLoginUrl.contains("?") ? "&" : "?") + serviceParameterName + "=" + serviceUrl ;
    }





    private static boolean requestIsOnStandardPort(ServerHttpRequest request) {
        int serverPort = request.getURI().getPort();
        return serverPort == 80 || serverPort == 443;
    }


    //Url编码
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(var2);
        }
    }

    //重定向方法
    public static Mono<Void> redirect(ServerWebExchange exchange, String urlToRedirectTo){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", urlToRedirectTo);
        log.info("重定向地址：{}", urlToRedirectTo);
        return exchange.getResponse().setComplete();


    }

}
