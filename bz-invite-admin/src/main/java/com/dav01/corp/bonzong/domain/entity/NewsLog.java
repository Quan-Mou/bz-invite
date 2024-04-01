package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;

import lombok.Data;

/**
 * (NewsLog)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
public class NewsLog {
    //新闻日志ID
    private Integer newsLogId;
    //操作用户ID
    private Integer employeeId;
    //操作类型（0：增加、1：删除、2：修改）
    private Integer operationType;
    //操作时间
    private Date operationTime;
    //操作的ID
    private Integer targetId;


    }

