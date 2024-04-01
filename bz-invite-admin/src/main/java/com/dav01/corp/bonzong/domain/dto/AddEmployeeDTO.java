package com.dav01.corp.bonzong.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author: 权某人
 * @create: 2024-03-26 10:39
 * @Description:
 */
@Data
public class AddEmployeeDTO {
    @NotBlank(message = "员工姓名不能为空")
    //    员工姓名
    private String employeeName;
    //工号
    @NotBlank(message = "员工工号不能为空")
    private String accountNumber;
    //手机号
    @NotBlank(message = "员工手机号不能为空")
    private String phone;
    //身份证
    @NotBlank(message = "员工身份证不能为空")
    private String idCard;
    //是否负责人（0：是，1：不是）
    @NotEmpty(message = "请选择是否为负责人")
    private Integer isMain;
}
