package com.example.SHOP.easy.Controller.Admin;

import com.example.SHOP.easy.DTO.CategoryDTO;
import com.example.SHOP.easy.Entity.Category;
import com.example.SHOP.easy.Services.Admin.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
