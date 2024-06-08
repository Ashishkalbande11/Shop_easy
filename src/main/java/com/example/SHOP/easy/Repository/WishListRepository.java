package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {


    List<WishList> findAllByUserId(Long userId);
}
