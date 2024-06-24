package com.luckyowl.test.aop;

import java.lang.reflect.Method;

/**
 * @description 方法匹配器
 *
 * @author LuckyOwl-CN
 * @date 2024/6/24
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targerClass);
}
