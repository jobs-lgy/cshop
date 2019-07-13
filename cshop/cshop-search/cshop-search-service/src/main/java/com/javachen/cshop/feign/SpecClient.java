package com.javachen.cshop.feign;

import com.javachen.cshop.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author june
 * @createTime 2019-06-26 19:45
 * @see
 * @since
 */
@FeignClient(name="cshop-cshop-service")
public interface SpecClient extends SpecApi {
}
