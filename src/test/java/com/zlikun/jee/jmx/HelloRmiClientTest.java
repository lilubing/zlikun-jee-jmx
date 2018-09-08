package com.zlikun.jee.jmx;

import org.junit.Test;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * 以RMI方式提供JMX服务客户端测试
 *
 * @author zlikun
 * @date 2018-09-08 17:59
 */
public class HelloRmiClientTest {

    final String domainName = "HelloDomain";
    final int rmiPort = 1099;

    @Test
    public void client() throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException, IntrospectionException {

        // 配置连接
        JMXServiceURL url = new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://localhost:%d/%s", rmiPort, domainName));
        JMXConnector jmxc = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // 使用连接对象获取MBean信息
        System.out.println("1 - 打印所有domain名称：");
        /**
         * JMImplementation
         * java.util.logging
         * HelloDomain
         * java.lang
         * com.sun.management
         * java.nio
         */
        Arrays.stream(mbsc.getDomains()).forEach(System.out::println);

        // 打印
        System.out.println("2 - 打印MBean数量，设置、查询属性");
        // MBean Count = 23
        System.out.printf("MBean Count = %d%n", mbsc.getMBeanCount());
        ObjectName mBeanName = new ObjectName(String.format("%s:name=Hello", domainName));
        // 注意 Greeting 首字母是大写
        mbsc.setAttribute(mBeanName, new Attribute("Greeting", "重新设置问候语"));
        // greeting = 重新设置问候语
        System.out.printf("greeting = %s%n", mbsc.getAttribute(mBeanName, "Greeting"));

        // 通过代理执行MBean里的方法，注意打印是在服务端
        System.out.println("3 - 执行MBean里的方法");
        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mBeanName, HelloMBean.class, false);
        proxy.printHello();
        proxy.printHello("Ashe");
        // 通过RMI执行MBean里的方法，注意打印是在服务端
        mbsc.invoke(mBeanName, "printHello", null, null);
        mbsc.invoke(mBeanName, "printHello", new String[]{"Peter"}, new String[]{String.class.getName()});

        // 获取MBean信息
        System.out.println("4 - 获取MBean信息");
        MBeanInfo info = mbsc.getMBeanInfo(mBeanName);
        System.out.printf("Hello Class: %s%n", info.getClassName());
        System.out.println("打印全部属性：");
        /**
         * Hello Attribute:Greeting
         */
        for (int i = 0; i < info.getAttributes().length; i++) {
            System.out.printf("Hello Attribute:%s%n", info.getAttributes()[i].getName());
        }
        System.out.println("打印全部操作：");
        /**
         * Hello Operation:printGreeting
         * Hello Operation:printHello
         * Hello Operation:printHello
         */
        for (int i = 0; i < info.getOperations().length; i++) {
            System.out.printf("Hello Operation:%s%n", info.getOperations()[i].getName());
        }

        System.out.println("5 - 查询MBean信息");
        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
        /**
         * java.lang:type=OperatingSystem
         * java.lang:type=MemoryManager,name=Metaspace Manager
         * java.lang:type=MemoryPool,name=Metaspace
         * JMImplementation:type=MBeanServerDelegate
         * java.lang:type=MemoryPool,name=PS Old Gen
         * java.lang:type=ClassLoading
         * java.lang:type=Runtime
         * java.lang:type=GarbageCollector,name=PS Scavenge
         * com.sun.management:type=HotSpotDiagnostic
         * java.lang:type=Threading
         * java.lang:type=MemoryManager,name=CodeCacheManager
         * java.lang:type=MemoryPool,name=PS Eden Space
         * java.nio:type=BufferPool,name=mapped
         * java.lang:type=MemoryPool,name=Code Cache
         * java.lang:type=MemoryPool,name=Compressed Class Space
         * java.nio:type=BufferPool,name=direct
         * java.lang:type=MemoryPool,name=PS Survivor Space
         * HelloDomain:name=Hello
         * java.util.logging:type=Logging
         * com.sun.management:type=DiagnosticCommand
         * java.lang:type=GarbageCollector,name=PS MarkSweep
         * java.lang:type=Memory
         * java.lang:type=Compilation
         */
        set.forEach(oi -> System.out.println(oi.getObjectName()));

        // 释放连接
        jmxc.close();

    }

}
