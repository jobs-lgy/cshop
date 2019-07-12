/**
 * Copyright © 2019-Now imxushuai
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javachen.reposity;

import com.javachen.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandReposity extends JpaRepository<Brand, Long> {

    Page<Brand> findAll(Pageable pageable);

    Page<Brand> findAllByNameLike(String name, Pageable pageable);

    @Query("SELECT b FROM CategoryBrand cb, Brand b WHERE cb.id.categoryId = ?1 AND cb.id.brandId = b.id")
    List<Brand> findAllByCategoryId(Long categoryId);

    List<Brand> findAllByIdIn(List<Long> ids);
}
