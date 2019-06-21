package com.sisucon.loopdaliy_server.Security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationDetailSource extends WebAuthenticationDetailsSource {

    class MyAuthenticationDetails extends WebAuthenticationDetails{
        private String username;
        private String password;
        private HttpServletRequest request;


        MyAuthenticationDetails(String userName, String passWord, HttpServletRequest request1) {
            super(request1);
            this.username = userName;
            this.password = passWord;
            this.request = request1;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }
    }

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new MyAuthenticationDetails(context.getParameter("username"),context.getParameter("password"),context);
    }
}
