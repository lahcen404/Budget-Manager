package com.BudgetManager.BudgetManager.service;

import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;


    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }


    public Budget createBudget(Budget budget){
        return budgetRepository.save(budget);
    }


    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }


    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }




    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        budget.setCategory(budgetDetails.getCategory());
        budget.setLimitAmount(budgetDetails.getLimitAmount());
        budget.setSpentAmount(budgetDetails.getSpentAmount());
        budget.getTransactions();

        return budgetRepository.save(budget);

    }
}
