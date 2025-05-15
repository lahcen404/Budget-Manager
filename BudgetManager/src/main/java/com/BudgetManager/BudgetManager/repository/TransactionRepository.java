package com.BudgetManager.BudgetManager.repository;

import com.BudgetManager.BudgetManager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
