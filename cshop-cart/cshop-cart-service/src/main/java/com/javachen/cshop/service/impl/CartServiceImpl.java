package com.javachen.cshop.service.impl;

import com.javachen.cshop.pojo.Cart;
import com.javachen.cshop.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate;

    private static String KEY_PREFIX = "leyou:cart:uid:";

    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    /**
     * 添加购物车
     *
     * @param cart
     */
    @Override
    public void add(Cart cart) {

//
//        //1.获取用户
//        UserResponse userInfo = LoginInterceptor.getLoginUser();
//        //2.Redis的key
//        String key = KEY_PREFIX + userInfo.getId();
//        //3.获取hash操作对象
//        BoundHashOperations<String,Object,Object> hashOperations = this.stringRedisTemplate.boundHashOps(key);
//        //4.查询是否存在
//        Long skuId = cart.getSkuId();
//        Integer num = cart.getNum();
//        Boolean result = hashOperations.hasKey(skuId.toString());
//        if (result){
//            //5.存在，获取购物车数据
//            String json = hashOperations.get(skuId.toString()).toString();
//            cart = ObjectMapperUtils.parse(json,Cart.class);
//            //6.修改购物车数量
//            cart.setNum(cart.getNum() + num);
//        }else{
//            //7.不存在，新增购物车数据
//            cart.setUserId(userInfo.getId());
//            //8.其他商品信息，需要查询商品微服务
//            Sku sku = this.itemClient.querySkuById(skuId);
//            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(),",")[0]);
//            cart.setPrice(sku.getPrice());
//            cart.setTitle(sku.getTitle());
//            cart.setOwnSpec(sku.getOwnSpec());
//        }
//        //9.将购物车数据写入redis
//        hashOperations.put(cart.getSkuId().toString(),ObjectMapperUtils.serialize(cart));
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @Override
    public List<Cart> findAllCart() {
//        //1.获取登录的用户信息
//        UserResponse userInfo = LoginInterceptor.getLoginUser();
//        //2.判断是否存在购物车
//        String key = KEY_PREFIX + userInfo.getId();
//        if (!this.stringRedisTemplate.hasKey(key)) {
//            //3.不存在直接返回
//            return null;
//        }
//        BoundHashOperations<String,Object,Object> hashOperations = this.stringRedisTemplate.boundHashOps(key);
//        List<Object> carts = hashOperations.values();
//        //4.判断是否有数据
//        if (CollectionUtils.isEmpty(carts)){
//            return null;
//        }
//        //5.查询购物车数据
//        return carts.stream().map( o -> ObjectMapperUtils.parse(o.toString(),Cart.class)).collect(Collectors.toList());
        return null;
    }

    /**
     * 更新购物车中商品数量
     *
     * @param skuId
     * @param num
     */
    @Override
    public void updateNum(Long skuId, Integer num) {
//        //1.获取登录用户
//        UserResponse userInfo = LoginInterceptor.getLoginUser();
//        String key = KEY_PREFIX + userInfo.getId();
//        BoundHashOperations<String,Object,Object> hashOperations = this.stringRedisTemplate.boundHashOps(key);
//        //2.获取购物车
//        String json = hashOperations.get(skuId.toString()).toString();
//        Cart cart = ObjectMapperUtils.parse(json,Cart.class);
//        cart.setNum(num);
//        //3.写入购物车
//        hashOperations.put(skuId.toString(),ObjectMapperUtils.serialize(cart));
    }

    /**
     * 删除购物车中的商品
     *
     * @param skuId
     */
    @Override
    public void delete(String skuId) {
//        //1.获取登录用户
//        UserResponse userInfo = LoginInterceptor.getLoginUser();
//        String key = KEY_PREFIX + userInfo.getId();
//        BoundHashOperations<String,Object,Object> hashOperations = this.stringRedisTemplate.boundHashOps(key);
//        //2.删除商品
//        hashOperations.delete(skuId);
    }
}
