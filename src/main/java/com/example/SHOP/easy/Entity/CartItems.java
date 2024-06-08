package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.CartItemsDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long price;

    Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Orders orders;

    public CartItemsDTO getCartDTO(){
        CartItemsDTO cartItemsDTO = new CartItemsDTO();
        cartItemsDTO.setId(id);
        cartItemsDTO.setPrice(price);
        cartItemsDTO.setQuantity(quantity);
        cartItemsDTO.setProductId(product.getId());
        cartItemsDTO.setUserId(user.getId());
        cartItemsDTO.setProductName(product.getName());
        cartItemsDTO.setReturnedImg(product.getImg());

        return cartItemsDTO;
    }
}
