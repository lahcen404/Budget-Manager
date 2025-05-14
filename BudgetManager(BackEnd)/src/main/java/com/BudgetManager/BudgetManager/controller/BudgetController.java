package com.BudgetManager.BudgetManager.controller;

import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    public final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget){
        return budgetService.createBudget(budget);
    }

    @GetMapping
    public List<Budget> getAllBudgets(){
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id){
        return budgetService.getBudgetById(id).orElseThrow();
    }
    @PutMapping("/{id}")
    public Budget updateProduct(@PathVariable Long id, @RequestBody Budget budget) {
        return budgetService.updateBudget(id,budget);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        budgetService.deleteBudget(id);
    }
}
