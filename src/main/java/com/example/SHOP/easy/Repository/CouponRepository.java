package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon , Long> {

    boolean existsByCode(String code);

    Optional<Coupon> findByCode(String code);
}
