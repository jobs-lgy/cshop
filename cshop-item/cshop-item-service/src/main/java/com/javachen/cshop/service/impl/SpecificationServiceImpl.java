package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.common.web.advice.BusinessException;
import com.javachen.cshop.common.web.advice.ErrorCode;
import com.javachen.cshop.entity.Specification;
import com.javachen.cshop.reposity.SpecificationRepository;
import com.javachen.cshop.admin.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationRepository specificationRepository;

    /**
     * 按分类ID查询规格(此表中分类ID其实为该表的ID)
     *
     * @param categoryId 分类ID
     * @return 规格信息
     */
    public Specification findByCategoryId(Long categoryId) {
        return specificationRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SPEC_NOT_EXIST));

    }

    /**
     * 新增规格
     *
     * @param specification 规格信息
     * @return 规格信息
     */
    @Transactional
    public Specification add(Specification specification) {
        specificationRepository.save(specification);
        return specification;
    }

    /**
     * 编辑规格
     *
     * @param specification 规格信息
     * @return 规格信息
     */
    public Specification update(Specification specification) {
        specificationRepository.save(specification);
        return specification;
    }

    public void delete(Long id) {
        specificationRepository.deleteById(id);
    }

//    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
//        SpecParam param = new SpecParam();
//        param.setGroupId(gid);
//        param.setCid(cid);
//        param.setSearching(searching);
//        param.setGeneric(generic);
//        return this.specParamMapper.select(param);
//    }
}
