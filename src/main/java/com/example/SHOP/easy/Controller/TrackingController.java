package com.example.SHOP.easy.Controller;

import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.Services.Customer.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@RequestMapping("")
public class TrackingController {

    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDTO> searchOderByTrackingId(@PathVariable UUID trackingId){
        OrderDTO orderDTO = cartService.searchOrderByTrackingId(trackingId);

        if(orderDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDTO);
    }
}
