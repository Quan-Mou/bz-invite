package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.JobCategoryMapper;
import com.dav01.corp.bonzong.domain.entity.JobCategory;
import com.dav01.corp.bonzong.service.IJobCategoryService;
import org.springframework.stereotype.Service;

/**
 * (JobCategory)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
public class JobCategoryServiceImpl extends ServiceImpl<JobCategoryMapper, JobCategory> implements IJobCategoryService {

}

