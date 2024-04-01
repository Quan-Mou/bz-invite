package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (TNews)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
public class News {
    //新闻ID
    private Integer newsId;
    //新闻标题
    private String title;
    //新闻内容
    private String content;
    //图片地址
    private String img;
    //新闻发布者
    private String author;
    //逻辑删除字段（0：删除，1：正常）
    private Integer isDelete;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    }

