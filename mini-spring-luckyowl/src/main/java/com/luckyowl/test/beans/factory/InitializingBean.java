package com.luckyowl.test.beans.factory;

/**
 * @description 初始化bean接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/12
 **/
public interface InitializingBean {

    /**
     * 初始化方法-属性赋值后执行
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
