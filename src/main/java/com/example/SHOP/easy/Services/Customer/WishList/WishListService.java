package com.example.SHOP.easy.Services.Customer.WishList;

import com.example.SHOP.easy.DTO.WishListDTO;

import java.util.List;

public interface WishListService {

    WishListDTO addProductToWishList(WishListDTO wishListDTO);

    List<WishListDTO> getWishListByUserId(Long userId);
}
