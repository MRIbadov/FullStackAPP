package com.example.repository;

import com.example.budget_mage_pr.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserREpository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
