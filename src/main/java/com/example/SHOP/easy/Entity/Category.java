package com.example.SHOP.easy.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="category")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Lob//for large data
    String description;
}
