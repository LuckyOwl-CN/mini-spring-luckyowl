package com.luckyowl.test.beans;

/**
 * @description bean属性信息
 * <p>
 *     保存bean属性名[name]和属性值[value]
 * </p>
 *
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
