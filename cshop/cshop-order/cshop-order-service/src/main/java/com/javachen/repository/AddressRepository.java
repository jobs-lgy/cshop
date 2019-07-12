package com.javachen.repository;

import com.javachen.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("delete from Address a where a.id=?1 and a.userId=?2")
    void deleteByIdAndUserId(Long id, Long userId);

    List<Address> findAllByUserId(Long userId);

    Address findByIdAndUserId(Long id, Long userId);
}
