package com.example.Service.expenseService;

import com.example.budget_mage_pr.entities.User;
import com.example.DTO.UserDTO;
import com.example.repository.UserREpository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserREpository userRepository;

    @Override
    public User registerUser(UserDTO userDTO) {
        // Check if the user already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Create and save a new user without password encryption
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // No encryption
        return userRepository.save(user);
    }

    // Login existing user
    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        // Directly compare passwords (no encryption)
        if (!user.getPassword().equals(password)) {
            throw new EntityNotFoundException("Invalid username or password");
        }

        return user;
    }

    // Find user by ID
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    // Create a new user
    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }




    // Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user details
    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());

        return userRepository.save(existingUser);
    }

    // Delete user by ID
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }
}
