package com.example.SHOP.easy.DTO;

import com.example.SHOP.easy.Entity.CartItems;
import com.example.SHOP.easy.Entity.User;
import com.example.SHOP.easy.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

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

    String userName;

    List<CartItemsDTO> cartItems;

    String couponName;
}
