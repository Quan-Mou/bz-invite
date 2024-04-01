package com.dav01.corp.bonzong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddJobDTO;
import com.dav01.corp.bonzong.domain.entity.Job;

import java.util.List;

/**
 * (Job)表服务接口
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
public interface IJobService extends IService<Job> {

    /**
     * 新增岗位
     * @param jobCategory 岗位分类
     * @param job
     * @return
     */
    R insert(Integer jobCategory, AddJobDTO job);

    /**
     * 批量删除岗位
     * @param ids
     * @return
     */
    R batchDelete(List<Integer> ids);


    /**
     * 修改岗位信息
     * @param id
     * @return
     */
    R update(Job job);


    /**
     * 根据id查看岗位
     * @param id
     * @return
     */
    R getJobById(Integer id);

    /**
     * 查看岗位列表
     * @param categoryId 岗位分类
     * @param page 当期页码
     * @param pageSze 页数
     * @param jobName 岗位名
     * @return
     */
    R list(Integer categoryId, Integer page, Integer pageSze, String jobName);


}

