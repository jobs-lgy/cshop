# cshop

该项目是采用目前比较流行的SpringBoot/SpringCloud构建微服务电商项目，实现一套串联的微服务电商项目，涵盖从微服务电商需求讨论、数据库设计、技术选型、互联网安全架构、整合SpringCloud各自组件、分布式基础设施、使用Docker+k8s+jenkins实现微服务自动化部署等等。

# 分支说明：

- 1、v1

基于乐友商和蚂蚁学院微服务第四期项目开发并做了大量修改，使用的技术包括Spring Boot、Spring MVC、Spring Cloud、JPA、Redis、RabbitMQ、ElasticSearch等框架，主要是完成了后端项目开发，前端没实现。

项目代码：<https://github.com/javachen/cshop/tree/v1>

项目文档：[spring-cloud-cshop](https://javachen.github.io/wiki/spring-cloud-cshop/)

- 2、v2

将Spring Cloud切换到Spring Cloud Alibaba上来。

项目代码：<https://github.com/javachen/cshop/tree/v2>

- 3、master

用于开发的最新版本。

# 版本v1

## 技术选型

- 核心框架：Spring Boot + Spring Cloud
- ORM 框架：Spring Data JPA
- 数据库连接池：Alibaba Druid
- 数据库缓存：Redis Sentinel
- 消息中间件：RocketMQ
- 接口文档引擎：Swagger2
- 全文检索引擎：ElasticSearch
- 分布式链路追踪：Zipkin
- 分布式文件系统：FastDFS
- 分布式系统网关：Spring Cloud Gateway
- 分布式注册中心：Spring Cloud Netflix Eureka
- 分布式配置中心：Spring Cloud Config
- 分布式熔断降级：Spring Cloud Hystrix
- 分布式锁解决方案：Redission
- 分布式全局ID生成：雪花算法
- 分布式事务解决方案：LCN
- 分布式日志采集系统：ELK +kafka
- 分布式Session框架：Spring Session
- 反向代理负载均衡：Nginx

## 开发环境

- 操作系统：Mac
- 开发工具：Intellij IDEA
- 数据库：MySQL 5.7
- Java SDK：Oracle JDK 1.8.152

## 部署环境

- 操作系统：Centos7
- 虚拟化技术：VirtualBox + Vagrant + Docker

## 项目管理工具

- 项目构建：Maven + Nexus
- 代码管理：Git + GitLab
- 镜像管理：Docker Registry

## 持续集成

- 持续集成：GitLab

更多，请查看文档。
