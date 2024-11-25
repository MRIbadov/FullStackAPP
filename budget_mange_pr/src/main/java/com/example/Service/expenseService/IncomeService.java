package com.example.Service.expenseService;

import com.example.DTO.IncomeDTO;
import com.example.budget_mage_pr.entities.Income;
import com.example.budget_mage_pr.entities.User;

import java.util.List;
import java.util.Optional;

public interface IncomeService {
    Income createIncome(IncomeDTO incomeDTO, User user);

    Optional<Income> findById(Long id);

    List<Income> getAllIncomes();

    List<Income> getUserIncomes(Long userId);

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    void deleteIncome(Long id);

    Double getTotalIncome();
}
