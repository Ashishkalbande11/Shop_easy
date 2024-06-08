package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.WishListDTO;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    public WishListDTO getWishListDTO() {

        WishListDTO wishListDTO = new WishListDTO();

        wishListDTO.setId(id);
        wishListDTO.setProductId(product.getId());
        wishListDTO.setReturnedImg(product.getImg());
        wishListDTO.setProductName(product.getName());
        wishListDTO.setProductDescription(product.getDescription());
        wishListDTO.setPrice(product.getPrice());
        wishListDTO.setUserId(user.getId());

        return wishListDTO;

    }
}
