package com.example.SHOP.easy.Services.Admin.Category;

import com.example.SHOP.easy.DTO.CategoryDTO;
import com.example.SHOP.easy.Entity.Category;
import com.example.SHOP.easy.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
