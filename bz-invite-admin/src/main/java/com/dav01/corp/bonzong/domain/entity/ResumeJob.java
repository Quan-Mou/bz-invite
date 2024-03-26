package com.dav01.corp.bonzong.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (TResumeJob)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-26 19:14:06
 */
@Data
public class ResumeJob {
    //投递id
    private Long resumeId;
    //岗位id
    private Long jobId;
}

