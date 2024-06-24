package com.luckyowl.test.bean;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class Car {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
