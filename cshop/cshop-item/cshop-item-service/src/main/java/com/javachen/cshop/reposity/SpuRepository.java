package com.javachen.cshop.reposity;

import com.javachen.cshop.entity.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpuRepository extends JpaRepository<Spu, Long> {

    @Query(value = "SELECT u FROM Spu u WHERE u.title like %?1% or u.saleable =?2 and u.enable =1",
            countQuery = "SELECT count(u) FROM Spu u WHERE u.title like %?1% or u.saleable =?2 and u.enable =1")
    Page<Spu> findAllByTitleLikeOrSaleable(String title,Boolean saleable, PageRequest pageRequest);
}
