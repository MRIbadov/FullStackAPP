package com.example.Service.expenseService;

import com.example.budget_mage_pr.entities.User;
import com.example.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserDTO userDTO);



    List<User> getAllUsers();

    User updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User registerUser(UserDTO userDTO);
    User loginUser(String username, String password);
    Optional<User> findById(Long id);
}
