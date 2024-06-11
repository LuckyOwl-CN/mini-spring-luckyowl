package com.luckyowl.test.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性值到属性值列表中
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv){
        //遍历已有属性列表寻找是否存在已有name的属性
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())){
                //如果属性值name相同，覆盖原有属性值
                propertyValueList.set(i, pv);
                return;
            }
        }
        propertyValueList.add(pv);
    }

    /**
     * 将属性值列表转换为属性值数组
     * @return
     */
    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 获取指定属性名的属性值
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName){
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue pv = propertyValueList.get(i);
            if(pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }
}
