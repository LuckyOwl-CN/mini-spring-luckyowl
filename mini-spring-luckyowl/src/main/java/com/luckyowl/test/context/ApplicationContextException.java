package com.luckyowl.test.context;

import com.luckyowl.test.beans.BeansException;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public class ApplicationContextException extends BeansException {

    public ApplicationContextException(String msg) {
        super(msg);
    }

    public ApplicationContextException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
