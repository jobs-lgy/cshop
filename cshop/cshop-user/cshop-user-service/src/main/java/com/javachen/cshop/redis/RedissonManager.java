package com.javachen.cshop.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RedissonManager {

    private static String redis1Ip = null;
    private static Integer redis1Port = null;
    private Config config = new Config();
    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

    @PostConstruct
    private void init() {
        try {
//            config.useSingleServer().setAddress(new StringBuilder().append(redis1Ip).append(":").append(redis1Port).toString());
//
//            redisson = (Redisson) Redisson.create(config);

            log.info("初始化Redisson结束");
        } catch (Exception e) {
            log.error("redisson init error", e);
        }
    }

}
