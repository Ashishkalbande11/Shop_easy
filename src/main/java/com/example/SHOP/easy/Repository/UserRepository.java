package com.example.SHOP.easy.Repository;

import com.example.SHOP.easy.Entity.User;
import com.example.SHOP.easy.Enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole userRole);

}
