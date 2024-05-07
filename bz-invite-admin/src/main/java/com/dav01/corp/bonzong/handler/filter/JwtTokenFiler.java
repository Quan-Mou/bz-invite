package com.dav01.corp.bonzong.handler.filter;

import com.alibaba.fastjson.JSON;
import com.dav01.corp.bonzong.config.RedisKeyUserBuilder;
import com.dav01.corp.bonzong.config.RedisKeyUserIdBuilder;
import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.handler.Context;
import com.dav01.corp.bonzong.handler.exception.SystemException;
import com.dav01.corp.bonzong.util.JwtUtil;
import com.dav01.corp.bonzong.util.MySessionContext;
import com.dav01.corp.bonzong.util.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 权某人
 * @create: 2024-03-23 23:57
 * @Description:
 */
@Component
@Slf4j
public class JwtTokenFiler extends OncePerRequestFilter {


    @Autowired
    private RedisTemplate redisTemplate;


    private MySessionContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Object userId = request.getSession().getAttribute("userId");
        if(userId != null) {
            Object loginSessionId = redisTemplate.opsForValue().get(RedisKeyUserIdBuilder.builderUserKey(request.getSession().getAttribute("userId").toString()));
            if (loginSessionId != null && loginSessionId.equals(request.getSession().getId())) {
//            没问题，一台设备登录
            } else {
                R result = new R<>(450, "该账号在另一台设备上登录，您被强制下线，请您重新登陆！！！");
                WebUtils.renderString(response,JSON.toJSONString(result));
//             TODO:还要把保存的session删除。以及token当前的token失效
                request.getSession().removeAttribute("userId");

//                redisTemplate.opsForValue().set();
                return;
//                throw new SystemException(SystemEnum.ACCOUNT_OFFLINE);
//                throw new RuntimeException("该账号在另一台设备上登录，您被强制下线，请您重新登陆！！！");
            }
        }

        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)) {
//         没有token、认为是的访问的是登录接口
            filterChain.doFilter(request,response);
            return;
        }


//       针对退出登录后的token黑名单
        if (Boolean.TRUE.equals(redisTemplate.hasKey("token"))) {
            log.info("登录过期");
            throw new SystemException(SystemEnum.LOGIN_EXPIRE);
        }


        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
            String subject = claims.getSubject();
            log.debug("subject:{}",subject);

        } catch (Exception e) {
            R r = new R(SystemEnum.LOGIN_EXPIRE, null);
            logger.error("登录过期");
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSON.toJSONString(r));
            return;
        }
        CustomerUserDetails customerUserDetails = (CustomerUserDetails) redisTemplate.opsForValue().get(RedisKeyUserBuilder.builderUserKey(claims.getSubject()));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customerUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
