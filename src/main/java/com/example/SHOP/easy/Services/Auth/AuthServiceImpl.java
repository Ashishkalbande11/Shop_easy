package com.example.SHOP.easy.Services.Auth;

import com.example.SHOP.easy.DTO.SignupRequest;
import com.example.SHOP.easy.DTO.UserDTO;
import com.example.SHOP.easy.Entity.Orders;
import com.example.SHOP.easy.Entity.User;
import com.example.SHOP.easy.Enums.OrderStatus;
import com.example.SHOP.easy.Enums.UserRole;
import com.example.SHOP.easy.Repository.OrderRepository;
import com.example.SHOP.easy.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    public UserDTO createUser(SignupRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

//when new user registered defaults values assigned to it
        Orders orders = new Orders();
        orders.setAmount(0L);
        orders.setDiscount(0L);
        orders.setTotalAmount(0L);
        orders.setUser(createdUser);
        orders.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(orders);


        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());

        return userDTO;
    }

   public boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
   }

   @PostConstruct
   public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if(adminAccount == null){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
   }
}
