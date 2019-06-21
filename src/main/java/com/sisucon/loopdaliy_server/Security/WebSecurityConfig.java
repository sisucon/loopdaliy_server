package com.sisucon.loopdaliy_server.Security;

import com.sisucon.loopdaliy_server.Model.UserModelRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserModelRepository userModelRepository;
    public WebSecurityConfig(UserModelRepository userModelRepository){
        this.userModelRepository = userModelRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().formLogin().authenticationDetailsSource(new MyAuthenticationDetailSource()).loginProcessingUrl("/login").permitAll()
                .successHandler(new MyAuthenctiationSuccessHandler())
                .and()
                .logout().permitAll().and()
                .authenticationProvider(new BasicAuthenticationProvider(new BasicUserDetailService(userModelRepository)));
        http.csrf().disable();
    }
}
