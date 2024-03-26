package com.dav01.corp.bonzong.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
;import com.dav01.corp.bonzong.domain.entity.Job;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Job)表数据库访问层
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

}

