package com.luckyowl.test.aop;

/**
 * @description 类过滤器
 *
 * @author LuckyOwl-CN
 * @date 2024/6/24
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
