package com.example.Controller;

import com.example.DTO.IncomeDTO;
import com.example.Service.expenseService.IncomeService;
import com.example.Service.expenseService.UserService;

import com.example.budget_mage_pr.entities.Income;
import com.example.budget_mage_pr.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class IncomeController {

    private final IncomeService incomeService;
    private final UserService userService;

    // POST: Create a new income
    @PostMapping
    public ResponseEntity<?> createIncome(@RequestBody IncomeDTO incomeDTO) {
        try {
            User user = userService.findById(incomeDTO.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + incomeDTO.getUserId()));

            Income createdIncome = incomeService.createIncome(incomeDTO, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating income: " + e.getMessage());
        }
    }

    // GET: Retrieve all incomes
    @GetMapping("/all")
    public ResponseEntity<List<Income>> getAllIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    // GET: Retrieve incomes for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserIncomes(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getUserIncomes(userId));
    }

    // GET: Retrieve income by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
        try {
            Income income = incomeService.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));
            return ResponseEntity.ok(income);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // PUT: Update income by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        try {
            Income updatedIncome = incomeService.updateIncome(id, incomeDTO);
            return ResponseEntity.ok(updatedIncome);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE: Delete income by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // GET: Retrieve total income
    @GetMapping("/total")
    public ResponseEntity<?> getTotalIncome() {
        Double totalIncome = incomeService.getTotalIncome();
        return ResponseEntity.ok(totalIncome);
    }
}
