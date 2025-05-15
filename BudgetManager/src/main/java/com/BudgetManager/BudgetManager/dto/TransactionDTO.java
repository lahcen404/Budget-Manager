package com.BudgetManager.BudgetManager.dto;
import java.time.LocalDate;

  public class TransactionDTO {
    private Long id;
    private double amount;
    private LocalDate date;
    private String description;
    private Long categoryId;
    private Long budgetId;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, double amount, LocalDate date, String description, Long categoryId, Long budgetId) {
      this.id = id;
      this.amount = amount;
      this.date = date;
      this.description = description;
      this.categoryId = categoryId;
      this.budgetId = budgetId;
    }
    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public double getAmount() {
      return amount;
    }

    public void setAmount(double amount) {
      this.amount = amount;
    }

    public LocalDate getDate() {
      return date;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public Long getCategoryId() {
      return categoryId;
    }

    public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
    }

    public Long getBudgetId() {
      return budgetId;
    }

    public void setBudgetId(Long budgetId) {
      this.budgetId = budgetId;
    }

    @Override
    public String toString() {
      return "TransactionDTO{" +
        "id=" + id +
        ", amount=" + amount +
        ", date=" + date +
        ", description='" + description + '\'' +
        ", categoryId=" + categoryId +
        ", budgetId=" + budgetId +
        '}';
    }
  }

