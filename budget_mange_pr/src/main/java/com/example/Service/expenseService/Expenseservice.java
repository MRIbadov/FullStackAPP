package com.example.Service.expenseService;

import com.example.DTO.ExpenseDTO;
import com.example.budget_mage_pr.entities.Expense;
import com.example.budget_mage_pr.entities.User;

import java.util.List;

public interface  Expenseservice {

    Expense postExpense(ExpenseDTO expenseDTO, User user);


    List<Expense> getAllExpenses();

    Expense getExpenseByID(Long id);

    Expense updateExpense(Long id , ExpenseDTO expenseDTO);
    void deleteExpense(Long id);
   // Double getTotalExpenses();

    Double getTotalExpenses();
}
