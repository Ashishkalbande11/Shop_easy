package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.Orders;
import com.example.SHOP.easy.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

     Orders findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

     List<Orders> findAllByOrderStatusIn(List<OrderStatus> placed);

    List<Orders> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);

    Optional<Orders> findByTrackingId(UUID trackingId);


    Long countByOrderStatus(OrderStatus orderStatus);


    List<Orders> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus orderStatus);
}
