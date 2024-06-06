package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanjiahao03
 * @date 2024/6/5
 **/
public interface BeanFactory {

    /**
     * 获取bean
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

}
