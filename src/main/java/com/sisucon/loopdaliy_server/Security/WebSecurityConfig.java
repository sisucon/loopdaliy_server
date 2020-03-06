package com.sisucon.loopdaliy_server.Security;

import com.sisucon.loopdaliy_server.Model.UserModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserModelRepository userModelRepository;
    public WebSecurityConfig(UserModelRepository userModelRepository){
        this.userModelRepository = userModelRepository;
    }
    private Logger logger = (Logger) LoggerFactory.getLogger(getClass());


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().formLogin().authenticationDetailsSource(new MyAuthenticationDetailSource()).loginProcessingUrl("/login").permitAll()
                .successHandler(new MyAuthenctiationSuccessHandler()).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authentication) throws IOException, ServletException {
                logger.info(" unsuccess login");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("{\"result\":false,\"message\":\"用户名或密码错误\"}");
                response.getWriter().flush();
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        })
                .and()
                .logout().permitAll().and()
                .authenticationProvider(new BasicAuthenticationProvider(new BasicUserDetailService(userModelRepository)));
        http.csrf().disable();
    }
}
