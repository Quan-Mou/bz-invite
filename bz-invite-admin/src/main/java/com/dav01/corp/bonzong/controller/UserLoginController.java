package com.dav01.corp.bonzong.controller;


import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.LoginDTO;
import com.dav01.corp.bonzong.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录模块")
@RestController
@RequestMapping("admin")
@Slf4j
public class UserLoginController {


    @Autowired
    private LoginService loginService;

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
    public R login(@RequestBody @Validated LoginDTO login) {
        return loginService.Login(login);
    }




    @GetMapping("demo")
    public String  demo() {
        return "xxxx";
    }


}
