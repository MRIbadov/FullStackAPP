package com.example.Controller;

import com.example.DTO.ExpenseDTO;
import com.example.Service.expenseService.Expenseservice;
import com.example.Service.expenseService.UserService;
import com.example.budget_mage_pr.entities.Expense;
import com.example.budget_mage_pr.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {

    private final Expenseservice expenseService;
    private final UserService userService;

    // POST: Add an expense
    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO dto) {
        // Fetch the user by ID from UserService
        User user = userService.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));

        // Create the expense by passing the user to the service
        Expense createdExpense = expenseService.postExpense(dto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    // GET: Retrieve all expenses
    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // GET: Retrieve expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpenseByID(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // PUT: Update expense by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpenseById(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        try {
            Expense updatedExpense = expenseService.updateExpense(id, expenseDTO);
            return ResponseEntity.ok(updatedExpense);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE: Delete expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // GET: Retrieve total expenses by user ID
    @GetMapping("/total/{userId}")
    public ResponseEntity<?> getTotalExpenses(@PathVariable Long userId) {
        Double total = expenseService.getTotalExpenses();
        return ResponseEntity.ok(total);
    }
}
