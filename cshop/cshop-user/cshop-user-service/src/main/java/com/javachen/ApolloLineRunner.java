//package com.javachen.web.user;
//
//import com.ctrip.framework.apollo.Config;
//import com.ctrip.framework.apollo.ConfigChangeListener;
//import com.ctrip.framework.apollo.entity.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
///**
// * @version V1.0
// * @description: 项目启动开启分布式配置中心阿波罗监听
// * @author: 97后互联网架构师-余胜军
// * @contact: QQ644064779、微信yushengjun644 www.javachen.com
// * @date: 2019年1月3日 下午3:03:17
// * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
// * 私自分享视频和源码属于违法行为。
// */
//@Component
//@Slf4j
//public class ApolloLineRunner implements CommandLineRunner {
//    @ApolloConfig
//    private Config config;
//
//    @Override
//    public void run(String... args) throws Exception {
//        config.addChangeListener(new ConfigChangeListener() {
//
//            @Override
//            public void onChange(ConfigChangeEvent changeEvent) {
//                log.info("####分布式配置中心监听#####" + changeEvent.changedKeys().toString());
//            }
//        });
//    }
//
//}