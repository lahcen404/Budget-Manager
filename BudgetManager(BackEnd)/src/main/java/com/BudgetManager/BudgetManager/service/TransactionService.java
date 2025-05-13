package com.BudgetManager.BudgetManager.service;

import com.BudgetManager.BudgetManager.model.Transaction;
import com.BudgetManager.BudgetManager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // ➕ Ajouter une transaction
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


