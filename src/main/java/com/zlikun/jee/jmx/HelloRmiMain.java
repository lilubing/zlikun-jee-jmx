package com.zlikun.jee.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 使用RMI来提供JMX服务
 *
 * @author zlikun
 * @date 2018-09-08 17:50
 */
public class HelloRmiMain {

    /**
     * 通过 JConsole 连接，连接字符串：
     * service:jmx:rmi:///jndi/rmi://localhost:1099/HelloDomain
     * @param args
     */
    public static void main(String[] args) {

        final String domainName = "HelloDomain";
        final int rmiPort = 1099;

        // MBeanServerFactory.createMBeanServer() 方式不能使用JConsole连接（未验证）
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            // 注册一个Hello对象到MBeanServer上
            mbs.registerMBean(new Hello(), new ObjectName(domainName + ":name=Hello"));


            LocateRegistry.createRegistry(rmiPort);
            JMXServiceURL url = new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://localhost:%d/%s", rmiPort, domainName));
            JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            jmxConnector.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
