package com.sisucon.loopdaliy_server.Security;

import com.sisucon.loopdaliy_server.Model.UserModelRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BasicUserDetailService implements UserDetailsService {
    private UserModelRepository userModelRepository;

    BasicUserDetailService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (userModelRepository.countByUserName(s)>0){
            return userModelRepository.findUserModelByUserName(s);
        }else {
            return null;
        }
    }
}
