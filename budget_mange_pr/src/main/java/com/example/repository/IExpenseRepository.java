package com.example.repository;

import com.example.budget_mage_pr.entities.Expense;
import com.example.budget_mage_pr.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IExpenseRepository extends JpaRepository<Expense, Long> {
    List<Income> findByUserId(Long userId);
}
