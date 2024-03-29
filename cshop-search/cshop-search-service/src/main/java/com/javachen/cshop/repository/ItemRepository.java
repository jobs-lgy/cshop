package com.javachen.cshop.repository;

import com.javachen.cshop.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author june
 * @createTime 2019-06-26 19:43
 * @see
 * @since
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
}
