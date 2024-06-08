package com.example.SHOP.easy.Controller.Customer;

import com.example.SHOP.easy.DTO.WishListDTO;
import com.example.SHOP.easy.Services.Customer.WishList.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishListDTO wishListDTO){

        WishListDTO postedWishListDTO = wishListService.addProductToWishList(wishListDTO);

        if(postedWishListDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishListDTO);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishListDTO>> getWishListByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishListService.getWishListByUserId(userId));
    }
}
