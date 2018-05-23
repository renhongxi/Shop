package com.vito16.shop.repository;

import com.vito16.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    void deleteByOrderId(Integer orderId);
}
