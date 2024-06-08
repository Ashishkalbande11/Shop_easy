package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishListDTO {

    Long userId;

    Long productId;

    Long id;

    String productName;

    String productDescription;

    byte [] returnedImg;

    Long price;


}
