package com.dav01.corp.bonzong.service.impl;

import com.dav01.corp.bonzong.config.RedisKeyUserBuilder;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;
import com.dav01.corp.bonzong.domain.entity.Employee;
import com.dav01.corp.bonzong.handler.Context;
import com.dav01.corp.bonzong.service.LoginService;
import com.dav01.corp.bonzong.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author: 权某人
 * @create: 2024-03-21 19:52
 * @Description:
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public R Login(LoginDTO login) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(login.getUserName(),login.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(authenticate == null) {
            throw new RuntimeException("该用户不存在");
        }
        CustomerUserDetails customerUserDetails = (CustomerUserDetails) authenticate.getPrincipal();
        Employee employee = customerUserDetails.getEmployee();
        String jwt = JwtUtil.createJWT(employee.getEmployeeId().toString());
        log.debug("jwt:{}",jwt);


        redisTemplate.opsForValue().set(RedisKeyUserBuilder.builderUserKey(employee.getEmployeeId().toString()),customerUserDetails);
        Context.local.set(customerUserDetails);

        return new R(200,"登录成功",jwt);
    }
}
