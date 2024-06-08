package com.example.SHOP.easy.Services.Customer;

import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.DTO.ProductDetailsDTO;

import java.util.List;

public interface CustomerProductService {

    List<ProductDTO> getAllProducts();

    ProductDetailsDTO getProductDetailById(Long productId);

    List<ProductDTO> getAllProductsByTitle(String name);
}
