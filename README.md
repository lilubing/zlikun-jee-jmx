#### JMX的术语
- 管理资源（Manageable resource）
- 管理组件（MBean，managed bean）
- 管理组件服务器（MBean Server）
- JMX代理（JMX Agent）
- 协议适配器和连接器（Protocol adapters and connectors）
- 通知（Notification）

#### JMX的架构
- 分布层（Distributed layer）
- 代理层（Agent layer ）
- 指示层（Instrumentation layer ）
![](https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1536399741&di=c5975d6e263c87583ec74e6b79483ba0&src=http://s15.sinaimg.cn/mw690/00678oMMzy6SkDg69Aide)

#### MBean类型
- 标准MBeans（Standard MBeans）设计和实现是最简单的，这类MBean使用自己的方法名作为管理接口；
- 动态MBeans（Dynamic MBeans）必须实现一个指定的接口，由于动态MBeans在运行期间暴露它们的管理接口，因此更为灵活；
- 开放MBeans（Open MBeans）属于动态MBeans，这类MBean依靠基础数据类型来实现通用管理，并为友情用户进行自我声明；
- 模型MBeans（Model MBeans）同样也是动态MBeans，这类MBeans是完全可配置的，在运行期间进行自我声明；它们为资源动态工具提供一个一般性的，有默认行为的MBeans类。

#### StandardMBean
1. 定义一个MBean接口，接口命名必须以MBean结束，如：HelloMBean
2. 定义一个MBean接口实现类，类名必须是MBean接口前面部分，如：Hello
3. 创建MBeanServer，为其设置一个惟一标识（名称，字符串）
4. 将MBean注册到MBeanServer中，使用ObjectName来惟一标识
5. 创建一个Adapter测试访问，HtmlAdaptorServer类需要依赖jmxtools.jar，一般Maven仓库里不存在，要么自己上传、要么用本地库
6. HtmlAdaptorServer本身也是一个MBean，也需要被加入MBeanServer中

#### 使用`commons-modeler`实现MBean
参考：<https://blog.csdn.net/u013256816/article/details/52840067>

#### Tomcat JMX
- https://blog.csdn.net/dongdong2980/article/details/78476393
- https://www.cnblogs.com/f1194361820/p/4372767.html
- https://blog.csdn.net/wojiushiwo945you/article/details/73776351
- https://www.jianshu.com/p/803d3608c83f
- https://blog.csdn.net/qq_32541407/article/details/78697726
- https://www.cnblogs.com/dyllove98/p/4106315.html

#### Zabbix监控Tomcat
- https://www.cnblogs.com/crysmile/p/7071103.html
- https://blog.csdn.net/reblue520/article/details/52623604
- https://blog.csdn.net/Hu_wen/article/details/53587250?locationNum=14&fps=1
- https://www.cnblogs.com/zhang-shijie/p/5328669.html
- https://www.cnblogs.com/bugsbunny/p/7204011.html
- https://www.jianshu.com/p/bfeda2bba15a
- http://blog.51cto.com/xianglinhu/1757930
