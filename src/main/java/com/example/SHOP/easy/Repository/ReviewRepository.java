package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query(value = "SELECT r FROM Review r WHERE r.productId = :productId")
    List<Review> findAllByProductId(Long productId);
}
