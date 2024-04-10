package com.dav01.corp.bonzong.constant;

/**
 * @author: 权某人
 * @create: 2024-03-26 11:08
 * @Description:
 */


public enum JobEnum {




    JOB_CATEGORY_NOT_NULL(420,"岗位分类不能为空"),
    NOT_DELETE_SEND_JOB(421,"不能删除已投递过的岗位");

    private Integer code;
    private String msg;

    JobEnum(Integer code,String msg) {
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
