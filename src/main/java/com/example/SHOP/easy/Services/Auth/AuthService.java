package com.example.SHOP.easy.Services.Auth;

import com.example.SHOP.easy.DTO.SignupRequest;
import com.example.SHOP.easy.DTO.UserDTO;

public interface AuthService  {
    UserDTO createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
