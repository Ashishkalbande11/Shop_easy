package com.example.SHOP.easy.Entity;

import com.example.SHOP.easy.Enums.UserRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "users")
//@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob // to store large data
    @Column(columnDefinition = "longblob")
    byte img[];


}
