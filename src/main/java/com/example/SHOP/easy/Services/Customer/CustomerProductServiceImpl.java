package com.example.SHOP.easy.Services.Customer;

import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.DTO.ProductDetailsDTO;
import com.example.SHOP.easy.Entity.FAQ;
import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Entity.Review;
import com.example.SHOP.easy.Repository.FAQRepository;
import com.example.SHOP.easy.Repository.ProductRepository;
import com.example.SHOP.easy.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private  final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProductsByTitle(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    public ProductDetailsDTO getProductDetailById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();

            productDetailsDTO.setProductDTO(optionalProduct.get().getDTO());
            productDetailsDTO.setFaqdtoList(faqList.stream().map(FAQ::getFAQDTO).collect(Collectors.toList()));
            productDetailsDTO.setReviewDTOList(reviewList.stream().map(Review::getDTO).collect(Collectors.toList()));

            return productDetailsDTO;

        }

        return null;
    }
}
