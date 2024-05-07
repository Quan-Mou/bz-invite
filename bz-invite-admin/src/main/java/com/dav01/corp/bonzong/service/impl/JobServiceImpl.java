package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.annotation.LogOperation;
import com.dav01.corp.bonzong.constant.JobEnum;
import com.dav01.corp.bonzong.constant.LogOperationConstant;
import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.dao.mapper.JobMapper;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddJobDTO;
import com.dav01.corp.bonzong.domain.entity.Job;
import com.dav01.corp.bonzong.domain.entity.JobCategory;
import com.dav01.corp.bonzong.domain.entity.ResumeJob;
import com.dav01.corp.bonzong.domain.vo.LogOperatorVo;
import com.dav01.corp.bonzong.handler.exception.SystemException;
import com.dav01.corp.bonzong.service.IJobCategoryService;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private TResumeJobService resumeJobService;


    @Autowired

    private IJobCategoryService jobCategoryService;

    @LogOperation("INSERT")
    @Override
    public LogOperatorVo insert(Integer jobCategory, AddJobDTO job) {
        if(Objects.isNull(jobCategory)) {
           throw new  SystemException(JobEnum.JOB_CATEGORY_NOT_NULL.getCode(),JobEnum.JOB_CATEGORY_NOT_NULL.getMsg());
        }

        Job saveJob = BeanCopyUtil.copyBean(job, Job.class);
        saveJob.setJobId(jobCategory);
        this.save(saveJob);
        LogOperatorVo logOperatorVo = new LogOperatorVo();
        List<Integer> jobIds = new ArrayList<>();
        jobIds.add(saveJob.getJobId());
        logOperatorVo.setJobId(jobIds);
        logOperatorVo.setStatus(true);
        return logOperatorVo;
    }

    @LogOperation("DELETE")
    @Override
    public LogOperatorVo batchDelete(List<Integer> ids) {
        if(ids.size()>0) {
            List<Job> resumeJobs = this.listByIds(ids);

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
            this.removeByIds(ids);
        }

        LogOperatorVo logOperatorVo = new LogOperatorVo();
        logOperatorVo.setJobId(ids);
        logOperatorVo.setStatus(true);
        return logOperatorVo;
    }

    @LogOperation(value = "UPDATE")
    @Override
    public LogOperatorVo update(Job job) {

        if (Objects.isNull(job)) {
            return null;
        }
        this.update(job);
        LogOperatorVo logOperatorVo = new LogOperatorVo();
        List<Integer> jobIds = new ArrayList<>();
        jobIds.add(job.getJobId());
        logOperatorVo.setJobId(jobIds);
        logOperatorVo.setStatus(true);
        return logOperatorVo;
    }

    @Override
    public R getJobById(Integer id) {
        System.out.println(id);
        if(id == null) {
            return  null;
        }
        LambdaQueryWrapper<Job> jobLambdaQueryWrapper = new LambdaQueryWrapper<>();
        jobLambdaQueryWrapper.eq(Job::getJobId,id);
        Job job = this.getOne(jobLambdaQueryWrapper);
        return new R(SystemEnum.SUCCESS,job);
    }

    @LogOperation("update")
    @Override
    public R list(Integer categoryId, Integer page, Integer pageSze, String jobName) {
        LambdaQueryWrapper<Job> query = new LambdaQueryWrapper<>();
        JobCategory category = null;
        if(categoryId != null) {
            LambdaQueryWrapper<JobCategory> jobCategoryQuery = new LambdaQueryWrapper<>();
            jobCategoryQuery.eq(JobCategory::getJobCategoryId,categoryId);
            category = jobCategoryService.getOne(jobCategoryQuery);
            if(category == null) {
                return R.success(new ArrayList<>());
            }
        }

        if(category != null) {
            query.eq(Job::getJobCategoryId,category.getJobCategoryId());
        }

        if(StringUtils.hasText(jobName)) {
            query.and(q -> q.like(StringUtils.hasText(jobName),Job::getJobName,jobName));
        }

        Page objects = PageHelper.startPage(page, page);
        System.out.println(objects);

        List<Job> list = this.list(query);
        return R.success(list);
    }
}

