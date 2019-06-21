package com.sisucon.loopdaliy_server.Security;

import com.sisucon.loopdaliy_server.Model.UserModel;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/***
 * 框架的密码验证
 */
public class MyverifyAuthentication extends AbstractAuthenticationToken {
    private UserModel userModel;
    private String credentials;
    private Collection<? extends GrantedAuthority> authorities;

    public MyverifyAuthentication(UserModel userModel, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userModel = userModel;
        this.credentials = credentials;
        this.authorities = authorities;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return userModel;
    }
}
