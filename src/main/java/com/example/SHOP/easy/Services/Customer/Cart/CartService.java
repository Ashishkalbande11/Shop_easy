package com.example.SHOP.easy.Services.Customer.Cart;

import com.example.SHOP.easy.DTO.AddProductInCartDTO;
import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.DTO.PlaceOrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO applyCoupon(Long userId, String code);

    OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);

    List<OrderDTO> getMyPlacedOrders(Long userId);

    OrderDTO searchOrderByTrackingId(UUID trackingId);
}
