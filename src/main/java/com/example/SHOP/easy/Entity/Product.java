package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.DTO.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(name="product")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long price;

    @Lob
    String description;

    @Lob
    @Column(columnDefinition = "longblob")
    byte []img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Category category;


    public ProductDTO getDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setDescription(description);
        productDTO.setByteImg(img);
        productDTO.setCategoryId(category.getId());
        productDTO.setCategoryName(category.getName());
        return productDTO;

    }
}
