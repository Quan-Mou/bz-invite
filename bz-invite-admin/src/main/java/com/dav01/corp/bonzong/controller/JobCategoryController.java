package com.dav01.corp.bonzong.controller;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.entity.JobCategory;
import com.dav01.corp.bonzong.service.IJobCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 权某人
 * @create: 2024-04-10 22:05
 * @Description:
 */


@Api(tags = "岗位分类")
@RestController
@RequestMapping("jobCategory")
public class JobCategoryController {

    @Autowired
    private IJobCategoryService jobCategoryService;

    @ApiOperation(value = "查询所有岗位分类")
    @GetMapping("list")
    public R getAllCategory() {
        List<JobCategory> list = jobCategoryService.list();
        return R.success(list);
    }




}
