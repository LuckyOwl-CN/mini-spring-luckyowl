package com.luckyowl.test.beans.factory;

/**
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class HelloService {

    private String name;

    private String message;

    private Long code;

    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return this.code;
    }
}
