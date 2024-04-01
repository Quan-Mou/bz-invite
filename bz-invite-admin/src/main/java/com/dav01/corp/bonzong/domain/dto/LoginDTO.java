package com.dav01.corp.bonzong.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: 权某人
 * @create: 2024-03-21 19:45
 * @Description:
 */

@Data
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
}
