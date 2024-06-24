package com.luckyowl.test.bean;

import com.luckyowl.test.beans.factory.DisposableBean;
import com.luckyowl.test.beans.factory.InitializingBean;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class Person implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    public void customInitMethod(){
        System.out.println("customInitMethod");
    }

    public void customDestroyMethod(){
        System.out.println("customDestroyMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}
