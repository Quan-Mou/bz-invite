package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (JobCategory)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
public class JobCategory implements Serializable {
    //岗位分类ID
    private Integer jobCategoryId;
    //分类名称
    private String jobCategoryName;
    //    岗位id
    private Integer jobId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

