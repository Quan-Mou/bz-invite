package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.ResumeMapper;
import com.dav01.corp.bonzong.domain.entity.Job;
import com.dav01.corp.bonzong.domain.entity.Resume;
import com.dav01.corp.bonzong.domain.vo.ResumeVo;
import com.dav01.corp.bonzong.service.IJobService;
import com.dav01.corp.bonzong.service.IResumeService;
import com.dav01.corp.bonzong.util.BeanCopyUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (TResume)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
@Slf4j
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements IResumeService {
    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private IJobService jobService;


    @Override
    public List<ResumeVo> list(Integer page, Integer pageSize, Integer jobId, String education, Date createTime, Integer isRead, String userName) {

        List<Job> jobList = jobService.list();
        List<Resume> resumes = resumeMapper.listByCondition(jobId, education, createTime, isRead, userName);

        List<ResumeVo> resumeVos = BeanCopyUtil.copyBeanList(resumes, ResumeVo.class);
        log.info("jobList: {}",jobList);
        log.info("resumes: {}",resumes);

        for (int i = 0;i<resumeVos.size();i++) {
            for (int j = 0;j<jobList.size();j++) {
                if(Objects.equals(resumeVos.get(i).getJobId(), jobList.get(j).getJobId())) {
                    resumeVos.get(i).setJobName(jobList.get(j).getJobName());
                }
            }
        }
        PageHelper.startPage(page,pageSize);
        return resumeVos;
    }
}

