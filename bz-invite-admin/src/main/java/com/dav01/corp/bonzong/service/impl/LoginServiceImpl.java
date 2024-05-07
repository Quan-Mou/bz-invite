package com.dav01.corp.bonzong.service.impl;

import com.dav01.corp.bonzong.config.RedisKeyUserBuilder;
import com.dav01.corp.bonzong.config.RedisKeyUserIdBuilder;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;
import com.dav01.corp.bonzong.domain.entity.Employee;
import com.dav01.corp.bonzong.handler.Context;
import com.dav01.corp.bonzong.service.LoginService;
import com.dav01.corp.bonzong.util.JwtUtil;
import com.dav01.corp.bonzong.util.MySessionContext;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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



    private MySessionContext context = MySessionContext.getSessionContext();

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public R Login(LoginDTO login, HttpServletRequest request, HttpSession session) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(login.getUserName(),login.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(authenticate == null) {
            throw new RuntimeException("该用户不存在");
        }
        CustomerUserDetails customerUserDetails = (CustomerUserDetails) authenticate.getPrincipal();
        Employee employee = customerUserDetails.getEmployee();
        String jwt = JwtUtil.createJWT(employee.getEmployeeId().toString());
        log.debug("jwt:{}",jwt);

//        context.addSession(employee.getEmployeeId().toString(),session);
        redisTemplate.opsForValue().set(RedisKeyUserBuilder.builderUserKey(employee.getEmployeeId().toString()),customerUserDetails);
//        request.getSession().setAttribute("user",employee.getEmployeeId());
//        Context.local.set(customerUserDetails);
//        request.setAttribute(employee.getEmployeeId().toString(),session);
//        session.setAttribute(employee.getEmployeeId().toString(),employee);

        request.getSession().setAttribute("userId",employee.getEmployeeId());
//        redisTemplate.opsForValue().set(employee.getEmployeeId().toString(),request.getSession().getId());
        redisTemplate.opsForValue().set(RedisKeyUserIdBuilder.builderUserKey(employee.getEmployeeId().toString()),request.getSession().getId());

//        redisTemplate.opsForValue().set(employee.getEmployeeId(),request.getSession().getId());
        return new R(200,"登录成功",jwt);
    }

    @Override
    public R lougout(String token) {
//        TODO:token 验证好像不需要在这里校验，应该在拦截中就要做了校验，所以这里暂时不做校验
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
            Date expiration = claims.getExpiration();
//          转换为秒
            long expirationSecond = expiration.getTime() / 1000;
            long currentSecond = System.currentTimeMillis() / 1000;
//          如果结果是一个正数，那么表示令牌还有多少时间才会过期。如果结果是负数或零，则表示令牌已经过期或刚刚过期
            long exp = expirationSecond - currentSecond;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomerUserDetails customerUserDetails = (CustomerUserDetails) authentication.getPrincipal();
            if(customerUserDetails == null || customerUserDetails.getEmployee() == null) {
                throw new RuntimeException("没有获取到userId");
            }
            Integer employeeId = customerUserDetails.getEmployee().getEmployeeId();
            redisTemplate.opsForValue().set(token,employeeId,exp, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return R.success();
    }
}
