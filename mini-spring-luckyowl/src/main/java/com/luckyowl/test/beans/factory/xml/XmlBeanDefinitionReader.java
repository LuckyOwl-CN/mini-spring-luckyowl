package com.luckyowl.test.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.config.BeanReference;
import com.luckyowl.test.beans.factory.support.AbstractBeanDefinitionReader;
import com.luckyowl.test.beans.factory.support.BeanDefinitionRegistry;
import com.luckyowl.test.core.io.Resource;
import com.luckyowl.test.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description 读取配置在xml中的bean定义
 *
 * @author LuckyOwl-CN
 * @date 2024/6/7
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * xml模版保留字符串—用于解析bean定义
     */
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 将Resource转成inputstream
     * @param resource
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        //使用try-with-resource自动关闭inputStream
        try(InputStream inputStream = resource.getInputStream()){
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | DocumentException e){
            throw new BeansException("获取XML文件[ " + resource + "]输入流时出现异常", e);
        }
    }

    /**
     * 读取inputStream加载bean定义
     * @param inputStream
     * @throws Exception
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException{
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element beans = document.getRootElement();
        List<Element> beanList = beans.elements(BEAN_ELEMENT);
        for (Element bean : beanList) {
            String beanId = bean.attributeValue(ID_ATTIBUTE);
            String beanName = bean.attributeValue(NAME_ATTRIBUTE);
            String className = bean.attributeValue(CLASS_ATTRIBUTE);
            String initMethodName = bean.attributeValue(INIT_METHOD_ATTRIBUTE);
            String destroyMethodName = bean.attributeValue(DESTROY_METHOD_ATTRIBUTE);

            Class<?> clazz;
            try{
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e){
                throw new BeansException("加载类[" + className + "]失败", e);
            }
            //id 优先于 name
            beanName = StrUtil.isEmpty(beanId) ? beanId : beanName;
            if(StrUtil.isEmpty(beanName)){
                //若id和name都为空，则使用类名第一个字母小写作为bean名称
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //bean定义中填充class对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //bean定义中填充初始化方法和销毁方法
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            List<Element> propertyList = bean.elements(PROPERTY_ELEMENT);
            for (Element property : propertyList) {
                String propertyNameAttribute = property.attributeValue(NAME_ATTRIBUTE);
                String propertyValueAttribute = property.attributeValue(VALUE_ATTRIBUTE);
                String propertyRefAttribute = property.attributeValue(REF_ATTRIBUTE);

                if(StrUtil.isEmpty(propertyNameAttribute)){
                    throw new BeansException("bean[" + beanName + "]中property的name属性不能为空");
                }

                Object value = propertyValueAttribute;
                if(StrUtil.isNotEmpty(propertyRefAttribute)){
                    value = new BeanReference(propertyRefAttribute);
                }
                PropertyValue propertyValue = new PropertyValue(propertyNameAttribute, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("bean[" + beanName + "]已存在，不可重复注册");
            }
            //解析xml注入完数据后，将beanName与bean定义注册进注册表
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
