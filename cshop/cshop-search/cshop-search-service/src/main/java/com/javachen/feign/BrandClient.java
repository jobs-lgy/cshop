package com.javachen.feign;

import com.javachen.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author june
 * @createTime 2019-06-26 19:45
 * @see
 * @since
 */
@FeignClient(name = "cshop-item-service")
public interface BrandClient extends BrandApi {
}