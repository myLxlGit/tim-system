package com.gre.lxl.framework.system.login.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author qixlin
 * @date 2021/06/23 11:15
 */
public class TimAuthToken extends AbstractAuthenticationToken {

    private final Object principal;

    private final Object credentials;

    @Getter
    @Setter
    private LoginMode mode;

    @Getter
    @Setter
    private String requestId;

    @Getter
    @Setter
    private TimAuthToken sourceToken;

    /**
     * 创建一个有鉴权的token
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public TimAuthToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
    }


    /**
     * 创建一个没有鉴权的token
     *
     * @param principal   principal
     * @param credentials credentials
     */
    public TimAuthToken(Object principal, Object credentials) {
        super(null);
        this.credentials = credentials;
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
