package com.javachen.cshop.cache.task;

import com.javachen.cshop.cache.redis.RedisShardedPoolUtil;
import com.javachen.cshop.cache.redis.RedissonManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CloseOrderTask {
    private static final String CLOSE_ORDER_TASK_LOCK = "close_order_task_lock";

    @Autowired
    private RedissonManager redissonManager;

    @PreDestroy
    public void delLock() {
        RedisShardedPoolUtil.del(CLOSE_ORDER_TASK_LOCK);

    }

    //    @Scheduled(cron="0 */1 * * * ?")//每1分钟(每个1分钟的整数倍)
    public void closeOrderTaskV1() {
        log.info("关闭订单定时任务启动");
        int hour = Integer.parseInt(System.getProperty("close.order.task.time.hour", "2"));
//        iOrderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");
    }

    //    @Scheduled(cron="0 */1 * * * ?")
    public void closeOrderTaskV2() {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(System.getProperty("lock.timeout", "5000"));

        Long setnxResult = RedisShardedPoolUtil.setnx(CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            //如果返回值是1，代表设置成功，获取锁
            closeOrder(CLOSE_ORDER_TASK_LOCK);
        } else {
            log.info("没有获得分布式锁:{}", CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV3() {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(System.getProperty("lock.timeout", "5000"));
        Long setnxResult = RedisShardedPoolUtil.setnx(CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            closeOrder(CLOSE_ORDER_TASK_LOCK);
        } else {
            //未获取到锁，继续判断，判断时间戳，看是否可以重置并获取到锁
            String lockValueStr = RedisShardedPoolUtil.get(CLOSE_ORDER_TASK_LOCK);
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)) {
                String getSetResult = RedisShardedPoolUtil.getSet(CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
                //再次用当前时间戳getset。
                //返回给定的key的旧值，->旧值判断，是否可以获取锁
                //当key没有旧值时，即key不存在时，返回nil ->获取锁
                //这里我们set了一个新的value值，获取旧的值。
                if (getSetResult == null || (getSetResult != null && StringUtils.equals(lockValueStr, getSetResult))) {
                    //真正获取到锁
                    closeOrder(CLOSE_ORDER_TASK_LOCK);
                } else {
                    log.info("没有获取到分布式锁:{}", CLOSE_ORDER_TASK_LOCK);
                }
            } else {
                log.info("没有获取到分布式锁:{}", CLOSE_ORDER_TASK_LOCK);
            }
        }
        log.info("关闭订单定时任务结束");
    }

    public void closeOrderTaskV4(String requestId) {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(System.getProperty("lock.timeout", "5000"));

        //尝试获取分布式锁，requestId用于删除锁时判断所有者
        String setnxResult = RedisShardedPoolUtil.setnx(CLOSE_ORDER_TASK_LOCK, requestId, lockTimeout);
        if ("OK".equals(setnxResult)) {
            closeOrder(CLOSE_ORDER_TASK_LOCK);
        } else {
            log.info("没有获取到分布式锁:{}", CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束");
    }

    //    @Scheduled(cron="0 */1 * * * ?")
    public void closeOrderTaskV5() {
        RLock lock = redissonManager.getRedisson().getLock(CLOSE_ORDER_TASK_LOCK);
        boolean getLock = false;
        try {
            if (getLock = lock.tryLock(0, 50, TimeUnit.SECONDS)) {
                log.info("Redisson获取到分布式锁:{},ThreadName:{}", CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
                int hour = Integer.parseInt(System.getProperty("close.order.task.time.hour", "2"));
//                iOrderService.closeOrder(hour);
            } else {
                log.info("Redisson没有获取到分布式锁:{},ThreadName:{}", CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            log.error("Redisson分布式锁获取异常", e);
        } finally {
            if (!getLock) {
                return;
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁");
        }
    }


    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 5);//有效期50秒，防止死锁
        log.info("获取{},ThreadName:{}", CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
        int hour = Integer.parseInt(System.getProperty("close.order.task.time.hour", "2"));
//        iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(CLOSE_ORDER_TASK_LOCK);
        log.info("释放{},ThreadName:{}", CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName());
        log.info("===============================");
    }


}
