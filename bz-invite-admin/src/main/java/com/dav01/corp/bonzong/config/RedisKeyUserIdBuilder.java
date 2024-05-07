package com.dav01.corp.bonzong.config;

/**
 * @author: 权某人
 * @create: 2024-04-10 20:09
 * @Description:
 */
public class RedisKeyUserIdBuilder {

    private static String  prefix = "userId::";
    public static String builderUserKey(String userId) {
        return prefix + userId;
    }







}
