package com.zlikun.jee.jmx;

import javax.management.*;

/**
 * 实现一个动态MBean，不需要使用MBean命名，只需要实现DynamicMBean接口即可，其内部实现主要通过反射来实现<br>
 * 其在用法上则与StandardMBean一致
 *
 * @author zlikun
 * @date 2018-09-08 18:28
 */
public class HelloDynamic implements DynamicMBean {
    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
