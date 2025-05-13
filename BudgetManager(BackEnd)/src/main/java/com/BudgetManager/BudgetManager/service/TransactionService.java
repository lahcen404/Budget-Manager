package com.BudgetManager.BudgetManager.service;

import com.BudgetManager.BudgetManager.model.Transaction;
import com.BudgetManager.BudgetManager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {


        @Autowired
  //  Ajouter une transaction
        private TransactionRepository transactionRepository;
        public Transaction addTransaction(Transaction transaction) {
            return transactionRepository.save(transaction);

        }
//
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    // update
    public Optional<Transaction> updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(existing -> {
            existing.setAmount(updatedTransaction.getAmount());
            existing.setDate(updatedTransaction.getDate());
            existing.setDescription(updatedTransaction.getDescription());
            return transactionRepository.save(existing);
        });
    }
    }


