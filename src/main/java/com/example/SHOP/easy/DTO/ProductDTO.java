package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductDTO {

    Long id;

    String name;

    Long price;

    String description;

    byte [] byteImg;

    Long categoryId;

    String categoryName;

    MultipartFile img;

    Long quantity;
}
