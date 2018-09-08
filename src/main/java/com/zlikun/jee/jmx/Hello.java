package com.zlikun.jee.jmx;

/**
 * 实现类则需要移除MBean后缀（JMX规范要求）
 *
 * @author zhanglikun
 * @version 1.0
 * @date 2018-09-03 19:25
 */
public class Hello implements HelloMBean {
    private String greeting;

    public Hello(String greeting) {
        this.greeting = greeting;
    }

    public Hello() {
        this.greeting = "hello world!";
    }

    @Override
    public String getGreeting() {
        return greeting;
    }

    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public void printGreeting() {
        System.out.println(greeting);
    }

    @Override
    public void printHello() {
        this.printHello("Boss");
    }

    @Override
    public void printHello(String name) {
        System.out.printf("Hello %s !%n", name);
    }
}
