package com.vito16.shop.repository;

import com.vito16.shop.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
//    void deleteByProductId(Integer id);
}
