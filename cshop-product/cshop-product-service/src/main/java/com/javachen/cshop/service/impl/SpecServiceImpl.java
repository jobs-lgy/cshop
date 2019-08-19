package com.javachen.cshop.service.impl;

import com.javachen.cshop.item.entity.Spec;
import com.javachen.cshop.reposity.SpecRepository;
import com.javachen.cshop.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecRepository specRepository;

    /**
     * 按分类ID查询规格(此表中分类ID其实为该表的ID)
     *
     * @param categoryId 分类ID
     * @return 规格信息
     */
    public Spec findByCategoryId(Long categoryId) {
        return specRepository.findByCategoryId(categoryId);

    }

    /**
     * 新增规格
     *
     * @param spec 规格信息
     * @return 规格信息
     */
    @Transactional
    public Spec add(Spec spec) {
        specRepository.save(spec);
        return spec;
    }

    /**
     * 编辑规格
     *
     * @param spec 规格信息
     * @return 规格信息
     */
    public Spec update(Spec spec) {
        specRepository.save(spec);
        return spec;
    }

    public void delete(Long id) {
        specRepository.deleteById(id);
    }
}
