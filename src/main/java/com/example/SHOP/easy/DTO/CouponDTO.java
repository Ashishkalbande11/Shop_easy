package com.example.SHOP.easy.DTO;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponDTO {

    Long id;

    String name;

    String code;

    Long discount;

    Date expiryDate;
}
