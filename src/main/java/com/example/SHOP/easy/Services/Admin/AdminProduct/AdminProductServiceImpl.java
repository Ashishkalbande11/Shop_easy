package com.example.SHOP.easy.Services.Admin.AdminProduct;

import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.Entity.Category;
import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Repository.CategoryRepository;
import com.example.SHOP.easy.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public ProductDTO addProduct(ProductDTO productDTO) throws IOException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImg(productDTO.getImg().getBytes());

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow();

        product.setCategory(category);

        return productRepository.save(product).getDTO();

    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProductsByName(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public ProductDTO getProductById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            return optionalProduct.get().getDTO();
        }else{
            return null;
        }
    }
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());

        if(optionalProduct.isPresent() && optionalCategory.isPresent()){
            Product product = optionalProduct.get();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setCategory(optionalCategory.get());

            if(productDTO.getImg() != null){
                product.setImg(productDTO.getImg().getBytes());
            }
            return productRepository.save(product).getDTO();
        }else{
            return null;
        }
    }
}
