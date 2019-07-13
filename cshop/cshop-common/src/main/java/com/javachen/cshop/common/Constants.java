package com.javachen.cshop.common;


public final class Constants {
    public static final String SERVICE_GOODS = "cshop-goods-service";
    public static final String SERVICE_SEARCH = "cshop-search-service";
    public static final String SERVICE_ORDER = "cshop-order-service";
    public static final String SERVICE_USER = "cshop-user-service";

    /**
     * reabbitmq queue name, exchange name and routingKey
     */
    public static final String EXCHANGE_DEFAULT_ITEM = "cshop.goods.exchange";
    public static final String EXCHANGE_SMS = "cshop.sms.exchange";

    public static final String ROUTING_KEY_INSERT_ITEM = "cshop.goods.insert";
    public static final String ROUTING_KEY_UPDATE_ITEM = "cshop.goods.update";
    public static final String ROUTING_KEY_DELETE_ITEM = "cshop.goods.delete";
    public static final String ROUTING_KEY_VERIFY_CODE_SMS = "cshop.sms.verify.code";


}
