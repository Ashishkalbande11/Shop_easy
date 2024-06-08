package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByNameContaining(String name);
}
