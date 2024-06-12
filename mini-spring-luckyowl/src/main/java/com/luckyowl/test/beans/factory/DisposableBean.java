package com.luckyowl.test.beans.factory;

/**
 * @description 销毁bean接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/12
 **/
public interface DisposableBean {

    /**
     * 销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
