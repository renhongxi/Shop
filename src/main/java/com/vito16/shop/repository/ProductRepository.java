package com.vito16.shop.repository;

import com.vito16.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByOrderByCreateTimeDesc();

    List<Product> findByOrderByCreateTimeAsc();

    @Query("FROM Product p")
    List<Product> findPopProducts();

}
