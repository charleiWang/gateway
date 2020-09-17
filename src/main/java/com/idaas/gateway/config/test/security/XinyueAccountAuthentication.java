//package com.idaas.gateway.config.test.security;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//public class XinyueAccountAuthentication extends AbstractAuthenticationToken {
//
//    private static final long serialVersionUID = 4161376524750338142L;
//    private Object credentials;
//    private String principal;
//
//    public XinyueAccountAuthentication(Object credentials, String principal, Collection<? extends GrantedAuthority> authorities) {
//        super(authorities);
//        this.credentials = credentials;
//        this.principal = principal;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return this.credentials;
//    }
//
//    @Override
//    public String getPrincipal() {
//        return this.principal;
//    }
//
//}
