package com.dav01.corp.bonzong.aspect;

import com.dav01.corp.bonzong.annotation.LogOperation;
import com.dav01.corp.bonzong.constant.LogOperationConstant;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.entity.Job;
import com.dav01.corp.bonzong.domain.entity.JobLog;
import com.dav01.corp.bonzong.domain.vo.LogOperatorVo;
import com.dav01.corp.bonzong.service.IJobLogService;
import com.dav01.corp.bonzong.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dav01.corp.bonzong.constant.LogOperationConstant.*;

/**
 * @author: 权某人
 * @create: 2024-04-15 13:52
 * @Description: 记录操作
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Resource
    private IJobLogService jobLogService;

    @AfterReturning(pointcut = "execution(* com.dav01.corp.bonzong.service.impl.JobServiceImpl.*(..))",returning = "result")
    public void logBefore(JoinPoint joinPoint, LogOperatorVo result) throws InstantiationException, IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogOperation logOperationAnnotation = AnnotationUtils.findAnnotation(methodSignature.getMethod(), LogOperation.class);
        if(logOperationAnnotation == null) {
            return;
        }

        if (!result.getStatus()) {
            return;
        }
        /**
         *  需要记录的日志：
         *  操作人ID：
         *  操作类型：0：增加、1：删除、2：修改
         *  操作id：操作的岗位id
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
        JobLog jobLog = new JobLog();
        jobLog.setEmployeeId(userDetails.getEmployee().getEmployeeId());
        jobLog.setOperationTime(new Date());

        String operatorType = logOperationAnnotation.value();
        switch (operatorType) {
            case "INSERT":
                jobLog.setOperationType(0);
                break;
            case "DELETE":
                jobLog.setOperationType(1);
                break;
            case "UPDATE":
                jobLog.setOperationType(2);
                break;
        }

        List<Integer> jobIds = result.getJobId();

//      批量操作
        if(jobIds.size()>1) {
            List<JobLog> jobLogs = new ArrayList<>();
            jobIds.forEach(jobId -> {
                JobLog log = BeanCopyUtil.copyBean(jobLog, JobLog.class);
                log.setJobLogId(jobId);
                jobLogs.add(log);
            });
            jobLogService.saveBatch(jobLogs);
        } else {

            System.out.println("jobIds.get(0) : " + jobIds.get(0));
            jobLog.setTargetId(jobIds.get(0));
            jobLogService.save(jobLog);
        }
    }
}
