package com.example.SHOP.easy.Services.Customer.Review;

import com.example.SHOP.easy.DTO.OrderedProductsResponseDTO;
import com.example.SHOP.easy.DTO.ProductDTO;
import com.example.SHOP.easy.DTO.ReviewDTO;
import com.example.SHOP.easy.Entity.*;
import com.example.SHOP.easy.Repository.OrderRepository;
import com.example.SHOP.easy.Repository.ProductRepository;
import com.example.SHOP.easy.Repository.ReviewRepository;
import com.example.SHOP.easy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;




    public OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId){
        Optional<Orders> optionalOrders = orderRepository.findById(orderId);

        OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();

        if(optionalOrders.isPresent()){
            orderedProductsResponseDTO.setOrderAmount(optionalOrders.get().getAmount());

            List<ProductDTO> productDTOList = new ArrayList<>();

            for(CartItems cartItems : optionalOrders.get().getCartItems()){
                ProductDTO productDTO = new ProductDTO();

                productDTO.setId(cartItems.getProduct().getId());
                productDTO.setName(cartItems.getProduct().getName());
                productDTO.setPrice(cartItems.getPrice());
                productDTO.setQuantity(cartItems.getQuantity());

                productDTO.setByteImg(cartItems.getProduct().getImg());

                productDTOList.add(productDTO);
            }
            orderedProductsResponseDTO.setProductDTOList(productDTOList);
        }
        return orderedProductsResponseDTO;
    }

    public ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review = new Review();

            review.setRating(reviewDTO.getRating());
            review.setDescription(reviewDTO.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDTO.getImg().getBytes());

            return reviewRepository.save(review).getDTO();
        }
        return null;
    }
}
