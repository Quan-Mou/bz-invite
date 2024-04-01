package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (TJobLog)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
@SuppressWarnings("serial")
public class JobLog  {
    //岗位日志ID
    private Integer jobLogId;
    //操作用户ID
    private Integer employeeId;
    //操作类型（0：增加、1：删除、2：修改）
    private Integer operationType;
    //操作时间
    private Date operationTime;
    //操作的ID
    private Integer targetId;



    }

