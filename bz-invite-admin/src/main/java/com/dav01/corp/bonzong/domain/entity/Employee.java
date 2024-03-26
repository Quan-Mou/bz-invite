package com.dav01.corp.bonzong.domain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * (Employee)表实体类
 *
 * @since 2024-03-21 19:56:27
 */
@Data
@SuppressWarnings("serial")
public class Employee extends Model<Employee> {
    //员工ID
    private Integer employeeId;
    //工号
    private String accountNumber;
    //手机号
    private String phone;
    //身份证
    private String idCard;
    //    员工姓名
    private String employeeName;
    //密码（明文存储）
    private String password;
    //邮箱
    private String email;
    //是否负责人（0：是，1：不是）
    private Integer isMain;
    //状态（0：在职，1：离职）
    private Integer status;
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
        return this.employeeId;
    }
    }

