package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.ReviewDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.awt.datatransfer.FlavorEvent;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long rating;

    @Lob
    String description;

    @Lob
    @Column(columnDefinition = "longblob")
    byte [] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    public ReviewDTO getDTO(){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setRating(rating);
        reviewDTO.setId(id);
        reviewDTO.setDescription(description);
        reviewDTO.setReturnedImg(img);
        reviewDTO.setProductId(product.getId());
        reviewDTO.setUserId(user.getId());
        reviewDTO.setUsername(user.getName());

        return reviewDTO;

    }
}
