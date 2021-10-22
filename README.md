Swan【天鹅】
================

#### 简单、好用的校验重复提交框架

# 功能
  * swan是一套解决重复新增、修改、删除等一切在有效时间段内处理重复的轮子

# 模块
  * swan-annotation : 注解打标模块

  * swan-common : 系统公共模块 比如：系统配置、系统常量、枚举、异常、存储模块[目前存储默认为redis]、token校验接口模块、公共类
  
  * swan-core : 功能实现模块 比如：token验证实现、spi加载实现、定时清理布隆过滤器实现、aop切面拦截              

  * swan-spring-boot-starter : 支持spring boot starter模块
  
  * swan-spring-cloud-demo : spring cloud集成demo
  
  * swan-spring-spring-demo : spring mvc集成demo
 
#  特征
   
   *  支持spring所有版本
   
   *  支持spring mvc、spring boot、spring cloud
   
   *  存储支持redis  部署方式支持redis单点、cluster集群、哨兵集群

   *  丰富的扩展支持 支持存储替换成其他产品需要自己实现 

# 环境要求 

  * JDK版本jdk1.8 +
  
  * Spring 环境
  
# 简介 

  1、Swan是一个校验重复的框架
  
    工作流程：
    
      1.1、header模式【适合重复验证的场景 比如：app客户端、其他不能使用cookie的场景】
    
	      第一步：前端先请求后端接口拿token，后端会把token放在header里面  
	      
	      第二步：如果保存成功key会记录到redis布隆过滤器里面，当再次重复保存会校验是否有以前保存过
	      
	      第三布：如果保存过程中有异常导致报错不成功，比如：逻辑异常、业务异常等前端需要重新获取key然后保存
	      
	  2.2、cookie模式【适合网页场景 比如：后台业务、互联网业务】     
	  
	      第一步：后端先把token放在cookie中，保存的时候http请求cookie带上token传递给后台  
	      
	      第二步：如果保存成功key会记录到redis布隆过滤器里面，当再次重复保存会校验是否有以前保存过
	      
	      第三布：如果保存过程中有异常导致报错不成功，比如：逻辑异常、业务异常等前端需要重新获取key然后保存
	      
      
    工作原理：
    
      第一步：框架会自动在header、cookie里面下发key，前端需要获取header、cookie里面的key，具体可以参考demo工程
      
      第二步：前端发起保存、修改会带上key到操作的目标方法中
      
      第三布：后端通过打标AOP切面会拦截保存方法，然后在布隆过滤器里面查询是否有操作过
      
      第四步：如果操作是重复的框架层面直接拒绝
      
      
  2、Swan使用技术
  
     SPI：解耦使用的存储技术、key生成方式模块化动态加载配置中的技术
     
     Redis：1、目前默认存储使用Redis布隆过滤器，原因性能O(1)使用内存低，比如：存储一亿个数据只需要12M的存储空间。2、框架默认开启分布式锁锁实现有redisson.lock提供。
     
     Spring：Swan是建立在Spring技术上的框架使用Spring AOP技术  
     
     
  3、对接简单
  
     Swan是绿色轻量级别产品。只需要引入jar+注释，详细见Swan-demo工程。如果你有兴趣可以下载源码，自定义符合你的应用场景。
     
     
  4、设计思想
  
     4.1、header模式设计思想：header模式合各种场景的http请求，由前端处理业务异常的方式。比较灵活，但是会给前端带来一定的工作量。
     
     4.2、cookie模式设计思想：cookie模式合适web场景的，做业务保存前先通过cookie下发token，然后后端自己处理异常。这个要在web场景使用，使用比较傻瓜化。
     
     4.3、什么时候使用锁：当两次提交时间间隔小于500毫秒，此时布隆过滤器还来不及初始化，此时建议使用分布式锁，框架默认是使用分布式锁。如果两次重复时间间隔大于500毫秒，为了最求性能可以容忍脏数据可以不使用分布式锁。
     
     4.4、为什么只实现Redis存储：性能、资源占用。redis布隆过滤器算法比较优秀有支持分布式故选择Redis。 [`布隆过滤器学习`](https://blog.csdn.net/god8816/article/details/109774406)
     
     4.5、关于布隆过滤器数据清除扩容：为了解决布隆过滤扩容问题，框架会自动清理掉48小时以前的数据。由于框架定位问题我们觉得48小时已经足够使用，如果你业务需要更长时间可以修改此项。
     
     
     
# 联系方式

  QQ：948351520
  
  微信：ppgou88
  
  邮箱：948351520@qq.com

