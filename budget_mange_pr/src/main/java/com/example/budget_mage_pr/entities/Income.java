package com.example.budget_mage_pr.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private Double amount;
    private LocalDate dateReceived;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User associated with the income
}
