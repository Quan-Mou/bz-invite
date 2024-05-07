package com.dav01.corp.bonzong.handler.exception;

import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 权某人
 * @create: 2024-03-22 20:11
 * @Description:
 */



@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(SystemException.class)
    public R systemExcepitonHandler(SystemException e) {
        System.out.println("systemExcepitonHandler");
        return null;
    }

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        if(e instanceof BindingResult) {
            Map map = new HashMap<String,Object>();
            List<FieldError> fieldErrors = ((BindingResult) e).getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            });
            return new R(SystemEnum.CHECK_ERROR,map);
        }
        if(e instanceof  SystemException) {
            log.debug("Execute Customer-SystemException");
            return new R(((SystemException) e).getCode(),((SystemException) e).getMsg());
        }

        log.error("e.getMessage() : {}",e.getMessage());
        return new R(401,e.getMessage());
    }
}
