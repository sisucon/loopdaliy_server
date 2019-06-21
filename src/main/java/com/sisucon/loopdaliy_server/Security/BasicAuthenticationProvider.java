package com.sisucon.loopdaliy_server.Security;

import com.sisucon.loopdaliy_server.Model.UserModel;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class BasicAuthenticationProvider implements AuthenticationProvider {
    private BasicUserDetailService basicUserDetailService;

    public BasicAuthenticationProvider(BasicUserDetailService basicUserDetailService) {
        this.basicUserDetailService = basicUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthenticationDetailSource.MyAuthenticationDetails myAuthenticationDetails = (MyAuthenticationDetailSource.MyAuthenticationDetails) authentication.getDetails();
        UserModel userModel  = (UserModel) basicUserDetailService.loadUserByUsername(myAuthenticationDetails.getUsername());
        if (userModel==null){
            return null;
        }
        if (!userModel.getPassWord().equals(myAuthenticationDetails.getPassword())){
            return null;
        }else {
            authentication = new MyverifyAuthentication(userModel,userModel.getPassWord(),userModel.getAuthorities());
            authentication.setAuthenticated(true);
            return authentication;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
