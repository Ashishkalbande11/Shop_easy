package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String orderDescription;

    Date date;

    Long amount;

    String address;

    String payment;

    OrderStatus orderStatus;

    Long totalAmount;

    Long discount;

    UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    List<CartItems> cartItems;

    public OrderDTO getOrderDTO(){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(id);
        orderDTO.setOrderDescription(orderDescription);
        orderDTO.setAddress(address);
        orderDTO.setTrackingId(trackingId);
        orderDTO.setAmount(amount);
        orderDTO.setDate(date);
        orderDTO.setOrderStatus(orderStatus);
        orderDTO.setUserName(user.getName());

        if(coupon != null){
            orderDTO.setCouponName(coupon.getName());
        }

        return orderDTO;
    }
}
