package com.dav01.corp.bonzong.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author: 权某人
 * @create: 2024-03-26 10:52
 * @Description:
 */
@Data
public class AddJobDTO {

    //岗位名称
    @NotBlank(message = "岗位名称不能为空")
    private String jobName;
    //岗位描述
    @NotBlank(message = "岗位描述不能为空")
    private String description;
    //最低薪资
    @NotBlank(message = "最低薪资不能为空")
    private Double minSalary;
    //最高薪资
    @NotBlank(message = "最高薪资不能为空")
    private Double maxSalary;
    //岗位类型（0：校园招聘；1：社会招聘；2：高级人才招聘）
    @NotBlank(message = "请选择岗位类型")
    private Integer type;
    //岗位分类ID
    @NotBlank(message = "请选择岗位分类")
    private Integer jobCategoryId;
    //招聘数量
    @NotBlank(message = "请输入招聘数量")
    private Integer jobNumber;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
