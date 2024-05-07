package com.dav01.corp.bonzong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 权某人
 * @create: 2024-04-15 14:10
 * @Description: 标注了该注解都需要记录操作日志
 */



@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface LogOperation {

    String value() default "";

}
