package com.example.Service.expenseService;

import com.example.DTO.ExpenseDTO;
import com.example.budget_mage_pr.entities.Expense;
import com.example.budget_mage_pr.entities.User;
import com.example.repository.IExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImp implements Expenseservice {

    private final IExpenseRepository expenseRepository;

    @Override
    public Expense postExpense(ExpenseDTO expenseDTO, User user) {
        Expense expense = new Expense();
        expense.setTitle(expenseDTO.getTitle());
        expense.setCategory(expenseDTO.getCategory());
        expense.setLocal_date(expenseDTO.getLocal_date());
        expense.setAmount(expenseDTO.getAmount());
        expense.setUser(user); // Set the user for the expense

        return expenseRepository.save(expense);
    }

    // Create a new expense
    public Expense createExpense(ExpenseDTO expenseDTO, User user) {
        Expense newExpense = new Expense();
        return saveOrUpdateExpense(newExpense, expenseDTO, user);
    }



    @Override
    public Expense getExpenseByID(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with ID: " + id));
    }

    // Update an existing expense
    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with ID: " + id));
        return saveOrUpdateExpense(existingExpense, expenseDTO, existingExpense.getUser());
    }

    // Helper method to save or update an expense
    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO, User user) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setCategory(expenseDTO.getCategory());
        expense.setLocal_date(expenseDTO.getLocal_date());
        expense.setAmount(expenseDTO.getAmount());
        expense.setUser(user);  // Associate the expense with the user
        return expenseRepository.save(expense);
    }

    // Get all expenses, sorted by date in descending order
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .sorted((e1, e2) -> e2.getLocal_date().compareTo(e1.getLocal_date()))
                .collect(Collectors.toList());
    }

    // Get an expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with ID: " + id));
    }

    // Delete an expense by ID
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    // Calculate total expenses by summing all amounts
    public Double getTotalExpenses() {
        return expenseRepository.findAll().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
