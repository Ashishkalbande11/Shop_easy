package com.example.SHOP.easy.Controller.Admin;

import com.example.SHOP.easy.Entity.Coupon;
import com.example.SHOP.easy.Exceptions.ValidatonException;
import com.example.SHOP.easy.Services.Admin.Coupon.AdminCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-admin-coupons")
@RequiredArgsConstructor
public class AdminCouponController {

    private final AdminCouponService adminCouponService;


    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){

        try{
            Coupon createdCoupon = adminCouponService.createCoupon(coupon);
            return ResponseEntity.ok(createdCoupon);
        }catch(ValidatonException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        return ResponseEntity.ok(adminCouponService.getAllCoupons());
    }
}
