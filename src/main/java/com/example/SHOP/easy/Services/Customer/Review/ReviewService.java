package com.example.SHOP.easy.Services.Customer.Review;

import com.example.SHOP.easy.DTO.OrderedProductsResponseDTO;
import com.example.SHOP.easy.DTO.ReviewDTO;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException;
}
