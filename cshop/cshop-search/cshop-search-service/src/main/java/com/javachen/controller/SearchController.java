package com.javachen.controller;

import com.javachen.common.response.PageResponse;
import com.javachen.domain.Item;
import com.javachen.domain.SearchRequest;
import com.javachen.domain.SearchResult;
import com.javachen.feign.SpuClient;
import com.javachen.model.SpuBo;
import com.javachen.repository.ItemRepository;
import com.javachen.service.SearchService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("page")
    public ResponseEntity<PageResponse<Item>> search(@RequestBody SearchRequest searchRequest){
        SearchResult<Item> result = this.searchService.search(searchRequest);
        return ResponseEntity.ok(result);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建索引
        this.elasticsearchTemplate.createIndex(Item.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Item.class);

        //加载数据
        List<SpuBo> list = new ArrayList<>();
        int page = 1;
        int row = 100;
        int size;
        do {
            //分页查询数据
            PageResponse<SpuBo> result = this.spuClient.findAllSpuByPage(page, row, null, true, null).getData();
            List<SpuBo> spus = result.getData();
            size = spus.size();
            page ++;
            list.addAll(spus);
        }while (size == 100);

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
