package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailsDTO {

    ProductDTO productDTO;

    List<ReviewDTO> reviewDTOList;

    List<FAQDTO> faqdtoList;
}
