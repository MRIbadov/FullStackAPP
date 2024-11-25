package com.example.repository;

import com.example.budget_mage_pr.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IIncomeREpository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);


}
