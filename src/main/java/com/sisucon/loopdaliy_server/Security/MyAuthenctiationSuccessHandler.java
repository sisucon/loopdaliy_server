package com.sisucon.loopdaliy_server.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisucon.loopdaliy_server.Model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info(((UserModel)authentication.getPrincipal()).getUserName()+" success login");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"result\":true,\"message\":\"登录成功\"}");
        response.getWriter().flush();
    }
}
