package com.dav01.corp.bonzong.controller;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddJobDTO;
import com.dav01.corp.bonzong.domain.entity.Job;
import com.dav01.corp.bonzong.service.IJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 权某人
 * @create: 2024-03-25 23:18
 * @Description:
 */

@Api("岗位管理")
@RestController
@RequestMapping("/job")
public class JobManagerController {


    @Autowired
    private IJobService jobService;

    @ApiOperation(value = "新增岗位")
    @PostMapping("save")
    public R save( @RequestParam(name = "jobCategory") Integer jobCategory, @RequestBody AddJobDTO job) {
//        TODO：新增岗位

        return jobService.insert(jobCategory,job);
    }


    @ApiOperation(value = "批量删除岗位")
    @DeleteMapping ("batchDelete")
    public R batchDelete(@RequestParam(name = "ids") List<Integer> ids) {
//        TODO：批量删除岗位
        return jobService.batchDelete(ids);
    }



    @ApiOperation(value = "修改岗位信息")
    @PutMapping("update")
    public R update(@RequestBody Job job) {
//        TODO：修改岗位信息
        return jobService.update(job);
    }



    @ApiOperation(value = "查看岗位信息")
    @GetMapping("getJobById")
    public R getJobById(@RequestParam(name = "id") Integer id) {
//        TODO：查看岗位信息
        return jobService.getJobById(id);
    }

    @ApiOperation(value = "查看岗位列表")
    @GetMapping("list")
    public R list(
            @RequestParam(name = "categoryId",required = false) Integer categoryId,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "pageSize") Integer pageSze,
            @RequestParam(name = "jobName",required = false) String jobName

    ) {


//        TODO：查看岗位列表
        return jobService.list(categoryId,page,pageSze,jobName);
    }






















}
