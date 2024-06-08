package com.example.SHOP.easy.Controller.Admin;

import com.example.SHOP.easy.DTO.FAQDTO;
import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Services.Admin.AdminProduct.AdminProductService;
import com.example.SHOP.easy.Services.Admin.FAQ.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final FAQService faqService;

    private final AdminProductService adminProductService;

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        //here we have to call images so using model attribute instead of request body

        ProductDTO productDTO1 = adminProductService.addProduct(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOS = adminProductService.getAllProducts();

        return ResponseEntity.ok(productDTOS);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@PathVariable String name){
        List<ProductDTO> productDTOS = adminProductService.getAllProductsByName(name);

        return ResponseEntity.ok(productDTOS);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        boolean deleted = adminProductService.deleteProduct(productId);

        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDTO> postFAQ(@PathVariable Long productId, @RequestBody FAQDTO faqdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId,faqdto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
        ProductDTO productDTO = adminProductService.getProductById(productId);

        if(productDTO != null){
            return ResponseEntity.ok(productDTO);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDTO productDTO) throws IOException {
        ProductDTO updatedProduct = adminProductService.updateProduct(productId, productDTO);

        if(updatedProduct != null){
            return ResponseEntity.ok(updatedProduct);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
