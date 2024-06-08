package com.example.SHOP.easy.Services.Admin.Category;

import com.example.SHOP.easy.DTO.CategoryDTO;
import com.example.SHOP.easy.Entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    List<Category> getAllCategories();
}
