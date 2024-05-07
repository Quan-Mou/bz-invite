package com.dav01.corp.bonzong.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    //Jackson JSON数据处理类
    private  static ObjectMapper objectMapper = new ObjectMapper();

//    @Autowired
//    MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        System.out.println("有对象登录，您已被下线");


        // 1. 获取用户名
        UserDetails userDetails =
                (UserDetails)event.getSessionInformation().getPrincipal();

        AuthenticationException exception =
                new AuthenticationServiceException(
                        String.format("[%s] 用户在另外一台电脑登录,您已被下线", userDetails.getUsername()));




//        try {
//            // 当用户在另外一台电脑登录后,交给失败处理器回到认证页面
//            event.getRequest().setAttribute("toAuthentication" , true);
//            myAuthenticationFailureHandler
//                    .onAuthenticationFailure(event.getRequest(), event.getResponse(), exception);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
    }
}

