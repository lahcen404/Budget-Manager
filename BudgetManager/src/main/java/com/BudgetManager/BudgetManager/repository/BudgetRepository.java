package com.BudgetManager.BudgetManager.repository;

import com.BudgetManager.BudgetManager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

}
