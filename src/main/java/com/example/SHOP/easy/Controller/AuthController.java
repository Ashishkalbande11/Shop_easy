package com.example.SHOP.easy.Controller;

import com.example.SHOP.easy.DTO.AuthenticationRequest;
import com.example.SHOP.easy.DTO.SignupRequest;
import com.example.SHOP.easy.DTO.UserDTO;
import com.example.SHOP.easy.Entity.User;
import com.example.SHOP.easy.Repository.UserRepository;
import com.example.SHOP.easy.Services.Auth.AuthService;
import com.example.SHOP.easy.Util.JWT_Util;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JWT_Util jwtUtil;
    private final AuthService authService;

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws JSONException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString()
            );
            response.addHeader("Access-Control_Expose-Headers","Authorization");
            response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Origin, " + "X,Requested-With, CONTENT-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        UserDTO userDTO = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}