package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (Img)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Data
@SuppressWarnings("serial")
public class Img extends Model<Img> {
    //图片ID
    private Integer imgId;
    //新闻ID
    private Integer newsId;
    //图片路径
    private String imgPath;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.imgId;
    }
    }

