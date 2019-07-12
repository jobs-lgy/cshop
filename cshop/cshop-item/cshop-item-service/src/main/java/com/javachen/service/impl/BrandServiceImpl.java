package com.javachen.service.impl;

import com.javachen.common.exception.ErrorCode;
import com.javachen.common.exception.BusinessException;
import com.javachen.entity.Brand;
import com.javachen.entity.CategoryBrand;
import com.javachen.reposity.BrandReposity;
import com.javachen.reposity.CategoryBrandRespository;
import com.javachen.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandReposity brandReposity;

    @Autowired
    private CategoryBrandRespository categoryBrandRespository;

    /**
     * 分页查询品牌列表
     *
     * @param page   当前页码
     * @param rows   每页记录数
     * @param sortBy 排序字段
     * @param desc   是否降序
     * @param key    查询关键字
     * @return 品牌分页列表
     */
    public Page<Brand> findAllByPage(int page, int rows, String sortBy, Boolean desc, String key) {
        Page<Brand> brandPage = null;
        if (StringUtils.isEmpty(key)) {
            brandPage = brandReposity.findAll(new PageRequest(page, rows));
        } else {
            Sort sort = new Sort(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            brandPage = brandReposity.findAllByNameLike("%" + key + "%", new PageRequest(page, rows, sort));
        }

        return brandPage;
    }

    /**
     * 新增品牌
     *
     * @param brand      品牌
     * @param categories 品牌所属分类
     * @return 品牌
     */
    public Brand add(Brand brand, List<Long> categories) {
        brandReposity.save(brand);
        // 添加关联的品牌分类
        insertCategoryBrand(brand, categories);

        return brand;
    }

    /**
     * 编辑品牌
     *
     * @param brand      品牌
     * @param categories 品牌所属分类
     * @return 品牌
     */
    public Brand update(Brand brand, List<Long> categories) {
        // 更新品牌
        brandReposity.save(brand);
        // 更新关联的品牌分类
        categoryBrandRespository.deleteByBrandId(brand.getId());
        insertCategoryBrand(brand, categories);
        return brand;
    }


    /**
     * 新增品牌分类关联数据(品牌 1 -> n 分类)
     *
     * @param brand      品牌
     * @param categories 分类
     */
    private void insertCategoryBrand(Brand brand, List<Long> categories) {
        categories.forEach(categoryId -> {
            categoryBrandRespository.save((new CategoryBrand(categoryId, brand.getId())));
        });
    }

    /**
     * 删除品牌
     *
     * @param brandId 品牌ID
     * @return 被删除的品牌
     */
    public void deleteBrand(Long brandId) {
        // 查询要删除的品牌
        brandReposity.deleteById(brandId);
        // 删除品牌的关联分类数据
        categoryBrandRespository.deleteByBrandId(brandId);
    }

    public void deleteCategoryBrand(Long brandId) {
        // 删除品牌的关联分类数据
        categoryBrandRespository.deleteByBrandId(brandId);
    }

    /**
     * 按品牌ID查询品牌
     *
     * @param id 品牌
     * @return Brand
     */
    public Brand findById(Long id) {
        return brandReposity.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RRAND_NOT_EXIST));

    }


    @Override
    public List<Brand> findAllByCategoryId(Long categoryId) {
        return brandReposity.findAllByCategoryId(categoryId);
    }

    /**
     * 按ID查询品牌，id可以为多个
     *
     * @param ids id集合
     * @return 品牌集合
     */
    public List<Brand> findAllByIdIn(List<Long> ids) {
        return brandReposity.findAllByIdIn(ids);
    }
}
