package com.dav01.corp.bonzong.handler.filter;

import com.alibaba.fastjson.JSON;
import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.handler.Context;
import com.dav01.corp.bonzong.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 权某人
 * @create: 2024-03-23 23:57
 * @Description:
 */
@Component
@Slf4j
public class JwtTokenFiler extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        if(!StringUtils.hasText(token)) {
//         没有token、认为是的访问的是登录接口
            filterChain.doFilter(request,response);
            return;
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
        logger.info("token有效，为登录状态");
        CustomerUserDetails customerUserDetails = Context.local.get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customerUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
