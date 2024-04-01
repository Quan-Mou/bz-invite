package com.dav01.corp.bonzong.handler;

import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import lombok.Data;

/**
 * @author: 权某人
 * @create: 2024-03-25 12:34
 * @Description:
 */
public class Context {
    public static ThreadLocal<CustomerUserDetails> local = new ThreadLocal<>();
}
