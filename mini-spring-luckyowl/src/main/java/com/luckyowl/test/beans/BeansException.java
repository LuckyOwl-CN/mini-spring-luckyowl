package com.luckyowl.test.beans;

/**
 * @description 定义bean异常
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class BeansException extends RuntimeException {

    /**
     * 构造时传入异常信息msg给父类
     * @param msg
     */
    public BeansException(String msg){
        super(msg);
    }

    /**
     * 构造时传入异常信息msg和异常原因
     * @param msg
     * @param cause
     */
    public BeansException(String msg, Throwable cause){
        super(msg, cause);
    }
}
