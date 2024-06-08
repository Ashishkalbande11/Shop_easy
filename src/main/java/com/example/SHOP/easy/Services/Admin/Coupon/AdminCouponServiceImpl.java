package com.example.SHOP.easy.Services.Admin.Coupon;

import com.example.SHOP.easy.Entity.Coupon;
import com.example.SHOP.easy.Exceptions.ValidatonException;
import com.example.SHOP.easy.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidatonException("Coupon code allready exists !");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }
}
