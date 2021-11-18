package com.gre.lxl.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author qixlin
 * @date 2021/06/23 13:40
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    private String token;

    private UserCachedVO user;

    private Long loginTime;

    private Long expireTime;

    private String loginIp;

    private Long erpId;

    public LoginUser(UserCachedVO user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
