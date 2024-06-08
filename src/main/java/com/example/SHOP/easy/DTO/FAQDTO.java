package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FAQDTO {

    Long id;

    String questions;

    String answers;

    Long productId;

}
