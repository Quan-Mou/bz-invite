package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.JobLogMapper;
import com.dav01.corp.bonzong.domain.entity.JobLog;
import com.dav01.corp.bonzong.service.IJobLogService;
import org.springframework.stereotype.Service;

/**
 * (TJobLog)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

}

