package com.BudgetManager.BudgetManager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class BudgetDTO {

    @NotNull(message = "limitAmount is required")
    @Min(value = 0, message = "limitAmount must be >= 0")
    private Double limitAmount;

    @NotNull(message = "spentAmount is required")
    @Min(value = 0, message = "spentAmount must be >= 0")
    private Double spentAmount;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Double getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(Double spentAmount) {
        this.spentAmount = spentAmount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
