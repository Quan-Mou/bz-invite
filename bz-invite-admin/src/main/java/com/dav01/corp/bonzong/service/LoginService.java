package com.dav01.corp.bonzong.service;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: 权某人
 * @create: 2024-03-21 19:51
 * @Description:
 */
public interface LoginService {

    R Login(LoginDTO login, HttpServletRequest request, HttpSession session);


    R lougout(String token);

}
