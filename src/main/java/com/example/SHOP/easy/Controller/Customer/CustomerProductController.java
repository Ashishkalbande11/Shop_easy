package com.example.SHOP.easy.Controller.Customer;

import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.DTO.ProductDetailsDTO;
import com.example.SHOP.easy.Services.Admin.AdminProduct.AdminProductService;
import com.example.SHOP.easy.Services.Customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOS = customerProductService.getAllProducts();

        return ResponseEntity.ok(productDTOS);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@PathVariable String name){
        List<ProductDTO> productDTOS = customerProductService.getAllProductsByTitle(name);

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailsDTO> getProductDetailsById(@PathVariable Long productId){

        ProductDetailsDTO productDetailsDTO = customerProductService.getProductDetailById(productId);
        if(productDetailsDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDetailsDTO);
    }
}
