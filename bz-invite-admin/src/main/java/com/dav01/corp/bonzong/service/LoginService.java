package com.dav01.corp.bonzong.service;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;

/**
 * @author: 权某人
 * @create: 2024-03-21 19:51
 * @Description:
 */
public interface LoginService {

    R Login(LoginDTO login);

}
