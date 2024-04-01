package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.constant.JobEnum;
import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.dao.mapper.JobMapper;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddJobDTO;
import com.dav01.corp.bonzong.domain.entity.Job;
import com.dav01.corp.bonzong.domain.entity.ResumeJob;
import com.dav01.corp.bonzong.handler.exception.SystemException;
import com.dav01.corp.bonzong.service.IJobService;
import com.dav01.corp.bonzong.service.IResumeService;
import com.dav01.corp.bonzong.util.BeanCopyUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.NullableUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * (Job)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service()
@Slf4j
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {


    @Autowired
    private IResumeService resumeService;


    @Autowired
    private TResumeJobService resumeJobService;

    @Autowired
    private IJobService jobService;


    @Override
    public R insert(Integer jobCategory, AddJobDTO job) {
        if(Objects.isNull(jobCategory)) {
           throw new  SystemException(JobEnum.JOB_CATEGORY_NOT_NULL.getCode(),JobEnum.JOB_CATEGORY_NOT_NULL.getMsg());
        }
        Job saveJob = BeanCopyUtil.copyBean(job, Job.class);
        this.save(saveJob);
        return R.success();
    }

    @Override
    public R batchDelete(List<Integer> ids) {
        if(ids.size()>0) {
            List<Job> resumeJobs = jobService.listByIds(ids);
            resumeJobs.forEach(resumeJob -> {
                Integer jobId = resumeJob.getJobId();
                LambdaQueryWrapper<ResumeJob> query = new LambdaQueryWrapper<>();
                query.eq(ResumeJob::getJobId,jobId);
                ResumeJob one = resumeJobService.getOne(query);
                if(!Objects.isNull(one)) {
                    String mgs = JobEnum.NOT_DELETE_SEND_JOB.getMsg() + one.getJobId();
                    throw new SystemException(JobEnum.NOT_DELETE_SEND_JOB.getCode(),mgs);
                }
            });
            jobService.removeByIds(ids);
        }
        return R.success();
    }

    @Override
    public R update(Job job) {

        if (Objects.isNull(job)) {
            return null;
        }
        this.update(job);
        return R.success();
    }

    @Override
    public R getJobById(Integer id) {
        if(id == null) {
            return  null;
        }
        Job job = jobService.getById(id);
        return new R(SystemEnum.SUCCESS,job);
    }

    @Override
    public R list(Integer categoryId, Integer page, Integer pageSze, String jobName) {







        LambdaQueryWrapper<Job> query = new LambdaQueryWrapper<>();
        query.like(StringUtils.hasText(jobName),Job::getJobName,jobName);
        Page objects = PageHelper.startPage(page, page);
        System.out.println(objects);
        this.list(query);




        return null;
    }
}

