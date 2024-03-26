package com.dav01.corp.bonzong.handler.exception;

import com.dav01.corp.bonzong.constant.SystemEnum;
import lombok.Data;

/**
 * @author: 权某人
 * @create: 2024-03-22 20:43
 * @Description:
 */
@Data
public class SystemException extends RuntimeException  {

    private int code;
    private String msg;


    public SystemException(SystemEnum systemEnum) {
        super(systemEnum.getMsg());
        this.code = systemEnum.getCode();
        this.msg = systemEnum.getMsg();
    }

    public SystemException(Integer code, String mgs) {
        super(mgs);
        this.code = code;
        this.msg = msg;
    }




}
