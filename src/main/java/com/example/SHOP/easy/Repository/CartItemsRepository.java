package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    @Query("SELECT c FROM CartItems c WHERE c.product.id = :productId AND c.orders.id = :orderId AND c.user.id = :userId")
    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId,  Long orderId, Long userId);
}
