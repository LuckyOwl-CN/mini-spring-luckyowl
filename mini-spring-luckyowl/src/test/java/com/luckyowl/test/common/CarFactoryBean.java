package com.luckyowl.test.common;

import com.luckyowl.test.bean.Car;
import com.luckyowl.test.beans.factory.FactoryBean;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;


    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
