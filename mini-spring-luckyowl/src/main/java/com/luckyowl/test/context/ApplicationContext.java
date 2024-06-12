package com.luckyowl.test.context;

import com.luckyowl.test.beans.factory.HierarchicalBeanFactory;
import com.luckyowl.test.beans.factory.ListableBeanFactory;
import com.luckyowl.test.core.io.ResourceLoader;

/**
 * @description 应用上下文
 * <p>
 *     spring中较BeanFactory更先进的IOC容器
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/11
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
