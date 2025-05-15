package com.BudgetManager.BudgetManager.mapper;

import com.BudgetManager.BudgetManager.dto.BudgetDTO;
import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.model.Category;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {

    public Budget toEntity(BudgetDTO dto, Category category){
       Budget budget = new Budget();
       budget.setLimitAmount(dto.getLimitAmount());
       budget.setSpentAmount(dto.getSpentAmount());
       budget.setCategory(category);
        return budget;
    }
}
