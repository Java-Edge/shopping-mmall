# 项目架构图
![服务集群和分布式缓存系统架构](https://upload-images.jianshu.io/upload_images/16782311-a0a14076dd50a6b8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 1 项目说明
> 基于 SSM 的电商系统，包括前台商城系统及后台管理系统!

> 如果该项目对您有帮助，您可以点右上角 "Star" 支持一下 谢谢！

> 或者您可以 "follow" 一下，该项目将持续更新，不断完善功能!

> [项目交流QQ群](https://jq.qq.com/?_wv=1027&k=5UB4P1T)

> 如有问题或者好的建议可以在 Issues 中提!

# 2 项目简介
`shopping-mall`项目是一套电商系统，包括前台商城系统及后台管理系统，基于SSM实现。 前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块。 后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。
## 用户模块
横向越权、纵向越权、
MD5明文加密、guava缓存
高复用服务响应对象的设计思想和封装
分类模块
递归算法
复杂对象排重
无限层级树结构设计

## 商品模块
POJO、BO、VO抽象模型
高效分页及动态排序
FTP服务对接、富文本上传

## 购物车模块
商品总价计算复用封装
高复用的逻辑方法封装思想
解决商业运算丢失精度的坑

## 订单模块
安全漏洞解决方案
订单号生成规则
常量、枚举设计

## 收货地址
同步获取自增主键
数据绑定的对象绑定
越权问题升级巩固

## 支付模块
支付宝支付流程与集成
二维码生成，扫码支付

## 线上部署
云服务器vsftpd、nginx等配置
云服务器的配置与域名解析


# 3 技术选型
## 3.1 后端技术

| 技术+版本 | 说明 | 官网 |
| --- | --- | --- |
| Spring Boot | 容器+MVC框架 | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security | 认证和授权框架 | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| MyBatis | ORM框架 | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatisGenerator | 数据层代码生成 | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html) |
| PageHelper | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| Swagger-UI | 文档生产工具 | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
| Hibernator-Validator | 验证框架 | [http://hibernate.org/validator/](http://hibernate.org/validator/) |
| Elasticsearch | 搜索引擎 | [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch) |
| RabbitMq | 消息队列 | [https://www.rabbitmq.com/](https://www.rabbitmq.com/) |
| Redis | 分布式缓存 | [https://redis.io/](https://redis.io/) |
| MongoDb | NoSql数据库 | [https://www.mongodb.com/](https://www.mongodb.com/) |
| Docker | 应用容器引擎 | [https://www.docker.com/](https://www.docker.com/) |
| Druid | 数据库连接池 | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS | 对象存储 | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |
| JWT | JWT登录支持 | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| LogStash | 日志收集 | [https://github.com/logstash/logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder) |
| Lombok | 简化对象封装工具 | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |

## 3.2 前端技术

| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/) |
| Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/) |
| Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/) |
| Element | 前端UI框架 | [https://element.eleme.io/](https://element.eleme.io/) |
| Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios) |
| v-charts | 基于Echarts的图表框架 | [https://v-charts.js.org/](https://v-charts.js.org/) |
| Js-cookie | cookie管理工具 | [https://github.com/js-cookie/js-cookie](https://github.com/js-cookie/js-cookie) |
| nprogress | 进度条控件 | [https://github.com/rstacruz/nprogress](https://github.com/rstacruz/nprogress) |

## 3.3 开发工具

| 工具 | 说明 | 官网 |
| --- | --- | --- |
| IDEA | 开发IDE | [https://www.jetbrains.com/idea/download](https://www.jetbrains.com/idea/download) |
| RedisDesktop | redis客户端连接工具 | [https://redisdesktop.com/download](https://redisdesktop.com/download) |
| Robomongo | mongo客户端连接工具 | [https://robomongo.org/download](https://robomongo.org/download) |
| SwitchHosts | 本地host管理 | [https://oldj.github.io/SwitchHosts/](https://oldj.github.io/SwitchHosts/) |
| X-shell | Linux远程连接工具 | [http://www.netsarang.com/download/software.html](http://www.netsarang.com/download/software.html) |
| Navicat | 数据库连接工具 | [http://www.formysql.com/xiazai.html](http://www.formysql.com/xiazai.html) |
| PowerDesigner | 数据库设计工具 | [http://powerdesigner.de/](http://powerdesigner.de/) |
| Axure | 原型设计工具 | [https://www.axure.com/](https://www.axure.com/) |
| MindMaster | 思维导图设计工具 | [http://www.edrawsoft.cn/mindmaster](http://www.edrawsoft.cn/mindmaster) |
| ScreenToGif | gif录制工具 | [https://www.screentogif.com/](https://www.screentogif.com/) |
| ProcessOn | 流程图绘制工具 | [https://www.processon.com/](https://www.processon.com/) |
| PicPick | 屏幕取色工具 | [https://picpick.app/zh/](https://picpick.app/zh/) |

## 3.4 开发环境
| 工具 | 版本号 | 下载 |
| --- | --- | --- |
| JDK | 1.8 | [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) |
| Mysql | 5.7 | [https://www.mysql.com/](https://www.mysql.com/) |
| Redis | 3.2 | [https://redis.io/download](https://redis.io/download) |
| Elasticsearch | 2.4.6 | [https://www.elastic.co/downloads](https://www.elastic.co/downloads) |
| MongoDb | 3.2 | [https://www.mongodb.com/download-center](https://www.mongodb.com/download-center) |
| RabbitMq | 5.25 | [http://www.rabbitmq.com/download.html](http://www.rabbitmq.com/download.html) |
| nginx | 1.10 | [http://nginx.org/en/download.html](http://nginx.org/en/download.html) |

# 4 接口清单

![](https://upload-images.jianshu.io/upload_images/16782311-e949406535f2c369.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
