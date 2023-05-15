package com.example.demokankretnalast.repositories;

import com.example.demokankretnalast.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
