package com.example.SHOP.easy.Services.Customer.Cart;

import com.example.SHOP.easy.DTO.AddProductInCartDTO;
import com.example.SHOP.easy.DTO.CartItemsDTO;
import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.DTO.PlaceOrderDTO;
import com.example.SHOP.easy.Entity.*;
import com.example.SHOP.easy.Enums.OrderStatus;
import com.example.SHOP.easy.Exceptions.ValidatonException;
import com.example.SHOP.easy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO){
        Orders activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDTO.getProductId(), activeOrder.getId(), addProductInCartDTO.getUserId());

        if(optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already in cart");
        }else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDTO.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDTO.getUserId());
            if(optionalProduct.isPresent() && optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrders(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User or Product not found");
            }
        }
    }
    public OrderDTO getCartByUserId(Long userId){
        Orders activeOrders = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);

        List<CartItemsDTO> cartItemsDTOS = activeOrders.getCartItems().stream().map(CartItems::getCartDTO).collect(Collectors.toList());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAmount(activeOrders.getAmount());
        orderDTO.setId(activeOrders.getId());
        orderDTO.setOrderStatus(activeOrders.getOrderStatus());
        orderDTO.setDiscount(activeOrders.getDiscount());
        orderDTO.setTotalAmount(activeOrders.getTotalAmount());

        orderDTO.setCartItems(cartItemsDTOS);

        if(activeOrders.getCoupon() != null){
            orderDTO.setCouponName(activeOrders.getCoupon().getName());
        }

        return orderDTO;
    }
    public  OrderDTO applyCoupon(Long userId, String code){
        Orders activeOrders = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidatonException("Coupon not found !"));

        if(couponIsExpired(coupon)){
            throw new ValidatonException("Coupon get expired !");
        }
         double discountAmount = ((coupon.getDiscount()/100.0) * activeOrders.getAmount());

        double netAmount = activeOrders.getTotalAmount() - discountAmount;

        activeOrders.setAmount((long) netAmount);
        activeOrders.setDiscount((long) discountAmount);
        activeOrders.setCoupon(coupon);

        orderRepository.save(activeOrders);
        return activeOrders.getOrderDTO();
    }
    private  boolean couponIsExpired(Coupon coupon){
        Date currentDate = new Date();

        Date expiryDate = coupon.getExpiryDate();

        return expiryDate != null && currentDate.after(expiryDate);
    }

    public OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO){
        Orders activeOrders = orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDTO.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDTO.getProductId(), activeOrders.getId(), addProductInCartDTO.getUserId()
        );

        if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrders.setAmount(activeOrders.getAmount() + product.getPrice());
            activeOrders.setTotalAmount(activeOrders.getTotalAmount() + product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity()+1);

            if(activeOrders.getCoupon() != null){
                double discountAmount = ((activeOrders.getCoupon().getDiscount()/100.0) * activeOrders.getAmount());

                double netAmount = activeOrders.getTotalAmount() - discountAmount;

                activeOrders.setAmount((long) netAmount);
                activeOrders.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrders);

            return activeOrders.getOrderDTO();
        }
        return null;
    }
    public OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO){
        Orders activeOrders = orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDTO.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDTO.getProductId(), activeOrders.getId(), addProductInCartDTO.getUserId()
        );

        if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrders.setAmount(activeOrders.getAmount() - product.getPrice());
            activeOrders.setTotalAmount(activeOrders.getTotalAmount() - product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity()-1);

            if(activeOrders.getCoupon() != null){
                double discountAmount = ((activeOrders.getCoupon().getDiscount()/100.0) * activeOrders.getAmount());

                double netAmount = activeOrders.getTotalAmount() - discountAmount;

                activeOrders.setAmount((long) netAmount);
                activeOrders.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrders);

            return activeOrders.getOrderDTO();
        }
        return null;
    }

    public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO){
        Orders activeOrders = orderRepository.findByUserIdAndOrderStatus(placeOrderDTO.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser = userRepository.findById(placeOrderDTO.getUserId());

        if(optionalUser.isPresent()){
            activeOrders.setOrderDescription(placeOrderDTO.getOrderDescription());
            activeOrders.setAddress(placeOrderDTO.getAddress());
            activeOrders.setDate(new Date());
            activeOrders.setOrderStatus(OrderStatus.Placed);
            activeOrders.setTrackingId(UUID.randomUUID());

            orderRepository.save(activeOrders);

            Orders orders = new Orders();
            orders.setAmount(0L);
            orders.setDiscount(0L);
            orders.setTotalAmount(0L);
            orders.setUser(optionalUser.get());
            orders.setOrderStatus(OrderStatus.Pending);
            orderRepository.save(orders);

            return activeOrders.getOrderDTO();
        }

        return null;
    }

    public List<OrderDTO> getMyPlacedOrders(Long userId){
        return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)).stream().map(Orders::getOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO searchOrderByTrackingId(UUID trackingId){

        Optional<Orders> optionalOrders = orderRepository.findByTrackingId(trackingId);

        if(optionalOrders.isPresent()){
            return optionalOrders.get().getOrderDTO();
        }
        return null;
    }
}
