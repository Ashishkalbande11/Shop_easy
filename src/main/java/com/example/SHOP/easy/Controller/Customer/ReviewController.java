package com.example.SHOP.easy.Controller.Customer;

import com.example.SHOP.easy.DTO.OrderedProductsResponseDTO;
import com.example.SHOP.easy.DTO.ReviewDTO;
import com.example.SHOP.easy.Services.Customer.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDTO> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDTO reviewDTO) throws IOException {
        ReviewDTO reviewDTO1 = reviewService.giveReview(reviewDTO);
        if(reviewDTO1 == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDTO1);
    }
}
