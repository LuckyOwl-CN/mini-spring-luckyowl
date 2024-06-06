package com.luckyowl.test.beans.factory;

/**
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class HelloService {

    private String name;

    private String message;

    public String sayHello(){
        System.out.println("hello");
        return "hello";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
