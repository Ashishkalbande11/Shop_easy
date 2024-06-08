package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

    List<FAQ> findAllByProductId(Long productId);
}
