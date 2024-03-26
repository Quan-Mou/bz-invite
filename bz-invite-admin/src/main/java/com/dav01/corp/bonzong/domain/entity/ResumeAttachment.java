package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (TResumeAttachment)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
public class ResumeAttachment implements Serializable {
    //附件ID
    private Integer attachmentId;
    //附件内容
    private Object attachment;
    //类型（0：PDF，1：Word 文件）
    private Integer type;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

