package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.PagedResult;
import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.domain.Item;
import com.javachen.cshop.domain.SearchRequest;
import com.javachen.cshop.feign.SpuClient;
import com.javachen.cshop.item.model.vo.SpuBo;
import com.javachen.cshop.repository.ItemRepository;
import com.javachen.cshop.service.SearchService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class SearchController implements InitializingBean {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SpuClient spuClient;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping("search")
    public RestResponse<PagedResult<Item>> search(@RequestBody SearchRequest searchRequest) {
        PagedResult<Item> result = this.searchService.search(searchRequest);
        return RestResponse.success(result);
    }

    public void afterPropertiesSet() throws Exception {
        // 创建索引
        this.elasticsearchTemplate.createIndex(Item.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Item.class);

        //加载数据
        List<SpuBo> list = new ArrayList<>();
        int page = 1;
        int row = 100;
        int size = 0;
        do {
            RestResponse<PagedResult<SpuBo>> response = this.spuClient.findAllByPage(page, row, null, true, null, false);
            //分页查询数据
            if (response.isSuccess()) {
                PagedResult<SpuBo> result = response.getData();
                List<SpuBo> spus = result.getList();
                size = spus.size();
                page++;
                list.addAll(spus);
            }
        } while (size == 100);

        List<Item> itemList = new ArrayList<>();
        //遍历spu
        for (SpuBo spu : list) {
            try {
                Item item = this.searchService.buildItem(spu);
                itemList.add(item);
            } catch (IOException e) {
                System.out.println("查询失败：" + spu.getId());
            }
        }
        this.itemRepository.saveAll(itemList);
    }
}
