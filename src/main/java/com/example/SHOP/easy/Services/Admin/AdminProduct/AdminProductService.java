package com.example.SHOP.easy.Services.Admin.AdminProduct;

import com.example.SHOP.easy.DTO.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDTO addProduct(ProductDTO productDTO) throws IOException;

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsByName(String name);

    boolean deleteProduct(Long id);

    ProductDTO getProductById(Long productId);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws IOException;
}
