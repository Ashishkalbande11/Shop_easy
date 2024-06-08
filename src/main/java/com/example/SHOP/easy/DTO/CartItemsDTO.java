package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemsDTO {

    Long id;

    Long price;

    Long quantity;

    Long productId;

    Long orderId;

    String productName;

    byte [] returnedImg;

    Long userId;
}
