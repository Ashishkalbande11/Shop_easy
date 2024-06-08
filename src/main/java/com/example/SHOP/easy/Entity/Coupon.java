package com.example.SHOP.easy.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Table(name="coupons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String code;

    Long discount;

    Date expiryDate;

}
