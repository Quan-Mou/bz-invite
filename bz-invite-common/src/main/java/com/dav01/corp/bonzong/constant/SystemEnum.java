package com.dav01.corp.bonzong.constant;


import lombok.Data;

public enum SystemEnum {


    /**
     * 添加、修改、删除成功都可以返回这个
     */
    SUCCESS(200,"操作成功"),


    NOT_EMPLOYEE(401,"员工不存在"),

    LOGIN_EXPIRE(401,"登录过期"),

    CHECK_ERROR(400,"提交的数据不合法");










    private Integer code;
    private String msg;

    SystemEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }



}
