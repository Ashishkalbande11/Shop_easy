package com.example.SHOP.easy.Services.Admin.Coupon;

import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.Entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();


}
