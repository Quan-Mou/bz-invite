package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (TResume)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
public class Resume implements Serializable{
    //投递ID
    private Integer resumeId;
    //岗位ID （表结构不做约束，代码层面关联岗位表）
    private Integer jobId;
    //学校所在省份
    private String schoolProvince;
    //学历（约定用数字表示：1：大专，2：本科.......）
    private Integer education;
    //学科类目 （约定用数字表示 1: 法学，2：工学，3：理学.......）
    private Integer subject;
    //姓名
    private String userName;
    //电话
    private String phone;
    //学校名称
    private String schoolName;
    //专业 （自己自定义输入）
    private String major;
    //简历附件ID
    private Integer resumeSourceId;
    //是否已阅（0：已阅，1：未阅）
    private Integer isRead;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

