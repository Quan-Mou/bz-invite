package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

/**
 * (Job)表实体类
 *
 * @author
 * @since 2024-03-21 19:56:28
 */
@Data
public class Job implements Serializable {
    //岗位ID
    @TableId(type = IdType.AUTO)
    private Integer jobId;
    //岗位名称
    private String jobName;
    //岗位描述
    private String description;
    //最低薪资
    private Double minSalary;
    //最高薪资
    private Double maxSalary;
    //岗位类型（0：校园招聘；1：社会招聘；2：高级人才招聘）
    private Integer type;
    //岗位分类ID
    private Integer jobCategoryId;
    //招聘数量
    private Integer jobNumber;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //逻辑删除
    private byte isDelete;

}

