package com.dav01.corp.bonzong.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
;import com.dav01.corp.bonzong.domain.entity.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (TResume)表数据库访问层
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {


//    List<Resume> listByCondition();

    List<Resume> listByCondition(
            @Param(value = "jobId") Integer jobId,
            @Param(value = "education") String education,
            @Param(value = "createTime") Date createTime,
            @Param(value = "isRead") Integer isRead,
            @Param(value = "userName") String userName);
}

