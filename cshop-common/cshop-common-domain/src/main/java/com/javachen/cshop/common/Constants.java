package com.javachen.cshop.common;

public class Constants {
     public static final Integer REMOVED=-1;
    public static final Integer DISABLE=0;
    public static final Integer ENABLE=1;

    //异常码定义
    public static class ErrorCode {
        //通用状态码 00**
        public static class CommonCode {
            public static String SUCCESS = "0000";
            public static String USER_NO_LOGIN = "0001";
            public static String EXCEPTION = "0002";
        }
    }

    public static class Image {
        //0:无类型 1:轮播图 2:海报图
        public static class ImageType {
            //无类型
            public static Integer normal = 0;
            //轮播图
            public static Integer carousel = 1;
            //海报图
            public static Integer poster = 2;
        }

        //图片分类
        public static class ImageCategory {
            public static Integer user = 0; //用户模块
            public static Integer item = 1; //商品模块
        }
    }

    public static class Redis_Expire {
        public static final long DEFAULT_EXPIRE = 60;//80s 有慢sql，超时时间设置长一点
        public final static int SESSION_TIMEOUT = 2 * 60 * 60;//默认2h
        public final static int REPLACEABLE_TIME_RANGE = 32 * 60;
        public final static int REPLACEMENT_PROTECTION_TIMEOUT = SESSION_TIMEOUT - REPLACEABLE_TIME_RANGE;//默认1.5h
        public final static int REPLACEMENT_DELAY = 2 * 60;//默认2min
    }

    public static class OrderStatus {
        public final static Integer TOPAY = 0;//待支付
        public static final Integer SUCCESS = 2;//支付成功
        public final static Integer FAIL = -1;//支付失败
        public final static Integer ISNEEDINSURANCE_NO = 0;//不需要保险
        public final static Integer ISNEEDINSURANCE_YES = 1;//需要保险
        public final static Double NEEDINSURANCE_MONEY = 20.0;//保险金额
    }

    /**
     * 座位表状态常量
     */
    public static class SchedulerSeatStatus {
        public final static Integer SchedulerSeat_FREE = 1;//空闲待购买
        public final static Integer SchedulerSeat_TOPAY = 2;//锁定待付款
        public static final Integer SchedulerSeat_PAYSUCCESS = 3;//支付成功
    }

    public static class QueueName {
        public final static String TO_QG_QUEUE = "toQgQueue";
        public final static String TO_CREATE_ORDER = "toCreateOrder";

        public final static String TO_UPDATED_ORDER_QUEUE = "toUpdateOrderQueue";

        public final static String TO_UPDATED_GOODS_QUQUE = "toUpdateGoodsQueue";
    }

    public static class RabbitQueueName {
        public final static String TO_QG_QUEUE = "toQgQueue";
        public final static String TO_CREATE_ORDER = "toCreateOrder";
        public final static String TO_UPDATED_ORDER_QUEUE = "toUpdateOrderQueue";
        public final static String TO_UPDATED_GOODS_QUQUE = "toUpdateGoodsQueue";

        public final static String TO_RESET_SEAT_QUQUE = "toResetSeatQueue";//重置座位队列
        public final static String TO_DEL_ORDER_QUQUE = "toDelOrderQueue";//删除订单队列
        public final static String TO_RESET_LINKUSER_QUQUE = "toResetLinkUserQueue";//重置联系人队列

        public final static String TOPIC_EXCHANGE = "topicExchange";
    }

    public static class QgStatus {
        public final static String fail = "-1";//拿到商品
        public final static String getGoods = "1";//拿到商品
        public final static String getOrder = "2";//生成订单
        public final static String paySuccess = "3"; //支付成功
    }

    public static class WxpayStatus {
        public final static String DDZTYC = "4001";
        public final static String DDZFYC = "4002";
        public final static String DDYXYC = "4003";
    }

    //状态(0:锁定库存,1:成功支付,2:支付超时)

    public static class StockStatus {
        public final static Integer lock = 0;//锁定库存
        public final static Integer success = 1;//支付成功
        public final static Integer cancel = 2;//支付超时
    }

    public static class TOPIC {
        public final static String ORDER_CONSUMER = "order_consumer";
        public final static String ORDER_PROVIDER = "order_provider";
        public final static String ITEM_CONSUMER = "item_consumer";
        public final static String ITEM_PROVIDER = "item_provider";
        public final static String BASE_CONSUMER = "base_consumer";
        public final static String BASE_PROVIDER = "base_provider";
        public final static String PAY_CONSUMER = "pay_consumer";
        public final static String PAY_PROVIDER = "pay_provider";
        public final static String SCHEDULER_CONSUMER = "scheduler_consumer";
        public final static String SCHEDULER_PROVIDER = "scheduler_provider";
        public final static String USER_CONSUMER = "user_consumer";
        public final static String USER_PROVIDER = "user_provider";
        public final static String GATEWAY_ZUUL = "gateway_zuul";
        public final static String ITEM_SEARCH = "item_search";
    }

    public static final String USER_TOKEN_PREFIX = "userToken:";
    public static final String IMAGE_TOKEN_PREFIX = "imageCache:";

    //文件服务器前缀
    public static final String FILE_PRE = "http://192.168.9.151:8888/";

    public static final String DEFAULT_USER = "default_user.jpg";

    public static final String DEFAULT_NORMAL = "default_normal.jpg";

    public static final String DEFAULT_CAROUSEL = "default_carousel.jpg";

    public static final String DEFAULT_POSTER = "default_poster.png";

    public static final Integer DEFAULT_PAGE_NO = 2;

    public static final Integer DEFAULT_PAGE_SIZE = 10;

    public static class PayMethod {
        public final static int WEIXIN = 1;
        public final static int ZHIFUBAO = 2;
    }

    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";//死信关联的Exchange的名称
    public static final String DEAD_LETTER_ROUTINKEY = "deadRoutingKey";//与上述Exchange绑定时需要的routingkey
    public static final String DEAD_QUEUE = "dead.queue";//与上述Exchange绑定时需要的routingkey
}
