package com.dav01.corp.bonzong.controller;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.ResultPage;
import com.dav01.corp.bonzong.domain.entity.Resume;
import com.dav01.corp.bonzong.domain.vo.ResumeVo;
import com.dav01.corp.bonzong.service.IResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author: 权某人
 * @create: 2024-03-26 00:19
 * @Description: 应聘管理，投递的简历
 */



@Api(value = "应聘管理")
@RestController
@RequestMapping("resume")
public class ResumeManagerController {


    @Autowired
    private IResumeService resumeService;


    @ApiOperation("查看应聘列表")
    @GetMapping("list")
    public ResultPage list(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "jobId",required = false) Integer jobId,
            @RequestParam(name = "education",required = false) String education,
            @RequestParam(name = "createTIme",required = false) Date createTime,
            @RequestParam(name = "isRead",required = false) Integer isRead,
            @RequestParam(name = "userName",required = false) String userName
            ) {
//        TODO :查看应聘列表
        List<ResumeVo> list = resumeService.list(page, pageSize, jobId, education, createTime, isRead, userName);
//        TODO: 这里没有处理好分页
        System.out.println(list);
        ResultPage resultPage = new ResultPage();
        resultPage.setData(list);
        resultPage.setTotal(11);
        return resultPage;
    }



}
