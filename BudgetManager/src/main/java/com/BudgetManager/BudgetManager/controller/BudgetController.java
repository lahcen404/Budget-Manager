package com.BudgetManager.BudgetManager.controller;

import com.BudgetManager.BudgetManager.dto.BudgetDTO;
import com.BudgetManager.BudgetManager.mapper.BudgetMapper;
import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.model.Category;
import com.BudgetManager.BudgetManager.repository.CategoryRepository;
import com.BudgetManager.BudgetManager.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {


    private final BudgetService budgetService;
    private final CategoryRepository categoryRepository;
    private final BudgetMapper budgetMapper;

    @Autowired
    public BudgetController(BudgetService budgetService, CategoryRepository categoryRepository, BudgetMapper budgetMapper) {
        this.budgetService = budgetService;
        this.categoryRepository = categoryRepository;
        this.budgetMapper = budgetMapper;
    }

    @PostMapping
    public ResponseEntity<?> createBudget(@Valid @RequestBody BudgetDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Budget budget = budgetMapper.toEntity(dto, category);
        return ResponseEntity.ok(budgetService.createBudget(budget));
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
