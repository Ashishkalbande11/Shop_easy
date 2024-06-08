package com.example.SHOP.easy.Services.Customer.WishList;


import com.example.SHOP.easy.DTO.WishListDTO;
import com.example.SHOP.easy.Entity.Product;
import com.example.SHOP.easy.Entity.User;
import com.example.SHOP.easy.Entity.WishList;
import com.example.SHOP.easy.Repository.ProductRepository;
import com.example.SHOP.easy.Repository.UserRepository;
import com.example.SHOP.easy.Repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishListRepository wishListRepository;

    public WishListDTO addProductToWishList(WishListDTO wishListDTO){

        Optional<Product> optionalProduct = productRepository.findById(wishListDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDTO.getUserId());

        if(optionalProduct.isPresent() && optionalProduct.isPresent()){
            WishList wishList = new WishList();

            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).getWishListDTO();
        }
        return null;
    }

    public List<WishListDTO> getWishListByUserId(Long userId){
        return wishListRepository.findAllByUserId(userId).stream().map(WishList::getWishListDTO).collect(Collectors.toList());    }
}
