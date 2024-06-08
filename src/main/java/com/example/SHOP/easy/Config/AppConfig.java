//package com.example.SHOP.easy.Config;
//
//import com.example.SHOP.easy.DTO.SignupRequest;
//import com.example.SHOP.easy.DTO.UserDTO;
//import com.example.SHOP.easy.Services.Auth.AuthService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public AuthService authService(){
//        return new AuthService() {
//            @Override
//            public UserDTO createUser(SignupRequest signupRequest) {
//                return null;
//            }
//
//            @Override
//            public boolean hasUserWithEmail(String email) {
//                return false;
//            }
//        } ;
//    }
//}
