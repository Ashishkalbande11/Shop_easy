package com.example.SHOP.easy.DTO;

import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDTO {

    Long id;

    Long rating;


    String description;

    MultipartFile img;

    byte [] returnedImg;

    Long productId;

    Long userId;

    String username;

}
