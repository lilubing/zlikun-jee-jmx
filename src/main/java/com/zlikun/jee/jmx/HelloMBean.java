package com.zlikun.jee.jmx;

/**
 * 接口名必须以MBean结尾（JMX规范要求的）
 * @author zhanglikun
 * @version 1.0
 * @date 2018-09-03 19:23
 */
public interface HelloMBean {

    String getGreeting();

    void setGreeting(String greeting);

    void printGreeting();

    void printHello();

    void printHello(String name);
}
