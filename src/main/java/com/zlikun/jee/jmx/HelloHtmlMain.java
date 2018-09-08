package com.zlikun.jee.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * 使用HtmlAdaptorServer（HTTP）来提供JMX服务
 * @author  zlikun
 * @date    2018-09-08 17:44
 */
public class HelloHtmlMain {

    /**
     * 通过 http://localhost:8888/ 访问
     *
     * @param args
     */
    public static void main(String[] args) {

        MBeanServer mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
        try {
            ObjectName objectName = new ObjectName("HelloAgent:name=Hello");
            mbs.registerMBean(new Hello(), objectName);
            ObjectName adapterName = new ObjectName("HelloAgent:name=HtmlAdapter,port=9092");
            HtmlAdaptorServer adapter = new HtmlAdaptorServer();
            adapter.setPort(8888);
            mbs.registerMBean(adapter, adapterName);
            adapter.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
