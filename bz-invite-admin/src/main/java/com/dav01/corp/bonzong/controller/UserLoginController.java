package com.dav01.corp.bonzong.controller;


import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;
import com.dav01.corp.bonzong.service.LoginService;
import com.sun.net.httpserver.HttpServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录模块")
@RestController
@RequestMapping("admin")
@Slf4j
public class UserLoginController {


    @Autowired
    private LoginService loginService;


    /**
     * 全局session
     */
    public static Map<String, HttpSession> loginSession = new HashMap<>();


    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }


    /**
     * 登录
     * @return
     */

    @ApiImplicitParam(name = "使用工号或者手机号作为账号",value = "登录DTO",required = true)
    @ApiOperation("员工登录")
    @PostMapping("login")
    public R login(@RequestBody @Validated LoginDTO login, HttpServletRequest request, HttpSession session) {
        return loginService.Login(login,request,session);
    }



    @DeleteMapping("/logout")
    public R logout(@RequestHeader("token") String token) {
        System.out.println("token" + token);
       return loginService.lougout(token);
        /**
         * 1.获取请求头token
         * 2.验证是否过期-》过期抛出异常
         * 3.获取过期时间
         * 4.存入redis，token作为key，value就存用户id，ttl设置为token的过期时间
         *
         *
         * 在拦截器中拦截请求，获取token
         * redis查询该token是否存在
         * 存在：说明该token之前已用做退出，拒绝登录。
         * 不存在：可以放行
         */
    }
}
