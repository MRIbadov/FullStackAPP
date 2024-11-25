package com.example.DTO;

import com.example.budget_mage_pr.entities.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String password;
    private Timestamp createdate;


}
