package com.luckyowl.test.aop;

/**
 * @description 切点抽象接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/24
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
