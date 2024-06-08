package com.example.SHOP.easy.DTO;

import com.example.SHOP.easy.Enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;

}
