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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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
        } catch (IOException e){
            throw new BeansException("获取XML文件[ " + resource + "]输入流时出现异常", e);
        }
    }

    /**
     * 读取inputStream加载bean定义
     * @param inputStream
     * @throws Exception
     */
    protected void doLoadBeanDefinitions(InputStream inputStream){
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++){
            if(childNodes.item(i) instanceof Element){
                // ((Element) childNodes.item(i) ).getNodeName()- 获取Element下的nodeName
                if(BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())){
                    //解析bean标签
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);

                    Class<?> clazz = null;
                    try{
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e){
                        throw new BeansException("加载类[" + className + "]失败", e);
                    }
                    //id 优先于 name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if(StrUtil.isEmpty(beanName)){
                        //若id和name都为空，则使用类名第一个字母小写作为bean名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    //bean定义中填充class对象
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);

                    //bean定义中填充初始化方法和销毁方法
                    beanDefinition.setInitMethodName(initMethodName);
                    beanDefinition.setDestroyMethodName(destroyMethodName);

                    //bean定义中填充属性
                    NodeList beanChildNodes = bean.getChildNodes();
                    for(int j = 0; j < beanChildNodes.getLength(); j++){
                        if(beanChildNodes.item(j) instanceof  Element){
                            //若该element是property节点
                            if(PROPERTY_ELEMENT.equals(beanChildNodes.item(j).getNodeName())){
                                //解析property标签
                                Element property = (Element) beanChildNodes.item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                                if(StrUtil.isEmpty(nameAttribute)){
                                    throw new BeansException("bean[" + beanName + "]中property的name属性不能为空");
                                }

                                Object value = valueAttribute;
                                if(StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    //注册前判断注册表中是否存在该beanName的注册
                    if(getRegistry().containsBeanDefinition(beanName)){
                        throw new BeansException("bean[" + beanName + "]已存在，不可重复注册");
                    }
                    //解析xml注入完数据后，将beanName与bean定义注册进注册表
                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }


}
