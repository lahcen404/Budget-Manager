package com.BudgetManager.BudgetManager.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private double amount;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Budget getBudget() {
    return budget;
  }

  public void setBudget(Budget budget) {
    this.budget = budget;
  }
}
