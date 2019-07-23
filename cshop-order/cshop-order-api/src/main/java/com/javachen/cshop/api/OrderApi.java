package com.javachen.cshop.api;

import com.javachen.cshop.entity.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("order")
public interface OrderApi {

    @PostMapping
    List<Long> addOrder(@RequestParam("seck") String seck, @RequestBody @Valid Order order);


    /**
     * 修改订单状态
     *
     * @param id
     * @param status
     * @return
     */
    @PutMapping("{id}/{status}")
    Boolean updateOrderStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);
}
