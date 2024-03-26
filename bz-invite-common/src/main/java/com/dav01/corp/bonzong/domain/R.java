package com.dav01.corp.bonzong.domain;


import com.dav01.corp.bonzong.constant.SystemEnum;
import lombok.Data;


/**
 * 统一返回结果
 * Author:权某
 * @param <T>
 */
@Data
public class R<T> {
    private String msg;
    private Integer code;
    private T data;


    public R (Integer code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }




    public R (Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public R (SystemEnum systemEnum, T data) {
        this.code = systemEnum.getCode();
        this.msg = systemEnum.getMsg();
        this.data = data;
    }

    public R (SystemEnum systemEnum) {
        this.code = systemEnum.getCode();
        this.msg = systemEnum.getMsg();
    }

//    public static R error() {
//
//    }


}
