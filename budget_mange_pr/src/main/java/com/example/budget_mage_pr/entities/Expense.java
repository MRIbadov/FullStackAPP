package com.example.budget_mage_pr.entities;


import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Expense{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "local_date")
    @DateTimeFormat
    private Date local_date;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
