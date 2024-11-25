package com.example.Service.expenseService;

import com.example.DTO.IncomeDTO;
import com.example.budget_mage_pr.entities.Income;
import com.example.budget_mage_pr.entities.User;
import com.example.repository.IIncomeREpository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeServiceIMp implements IncomeService {


    private final IIncomeREpository incomeRepository;

    @Override
    public Income createIncome(IncomeDTO incomeDTO, User user) {
        Income income = new Income();
        income.setSource(incomeDTO.getSource());
        income.setAmount(incomeDTO.getAmount());
        income.setDateReceived(incomeDTO.getDateReceived());
        income.setUser(user);

        return incomeRepository.save(income);
    }

    @Override
    public Optional<Income> findById(Long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public List<Income> getUserIncomes(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    @Override
    public Income updateIncome(Long id, IncomeDTO incomeDTO) {
        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));

        existingIncome.setSource(incomeDTO.getSource());
        existingIncome.setAmount(incomeDTO.getAmount());
        existingIncome.setDateReceived(incomeDTO.getDateReceived());

        return incomeRepository.save(existingIncome);
    }

    @Override
    public void deleteIncome(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));
        incomeRepository.delete(income);
    }

    @Override
    public Double getTotalIncome() {
        return incomeRepository.findAll().stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }


}
