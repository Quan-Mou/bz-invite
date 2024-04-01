package com.dav01.corp.bonzong.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
;import com.dav01.corp.bonzong.domain.entity.JobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TJobLog)表数据库访问层
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Mapper
public interface JobLogMapper extends BaseMapper<JobLog> {

}

