/**
 * Copyright © 2019-Now imxushuai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javachen.model;

import com.javachen.entity.Sku;
import com.javachen.entity.Spu;
import com.javachen.entity.SpuDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SpuBo extends Spu {
    // 分类名称
    @Transient
    private String cname;

    // 品牌名称
    @Transient
    private String bname;

    // 商品描述
    @Transient
    private SpuDetail spuDetail;

    // 商品列表
    @Transient
    private List<Sku> skuList;
}