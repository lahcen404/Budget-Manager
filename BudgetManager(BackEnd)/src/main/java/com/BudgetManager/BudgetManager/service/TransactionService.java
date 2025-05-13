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

    // Filtrer par date
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findAll().stream()
                .filter(t -> t.getDate().equals(date))
                .collect(Collectors.toList());
    }

    //  Trier par montant
    public List<Transaction> getTransactionsSortedByAmount(boolean ascending) {
        return transactionRepository.findAll().stream()
                .sorted(ascending ?
                        Comparator.comparingDouble(Transaction::getAmount) :
                        Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    //   Trier par date
    public List<Transaction> getTransactionsSortedByDate(boolean ascending) {
        return transactionRepository.findAll().stream()
                .sorted(ascending ?
                        Comparator.comparing(Transaction::getDate) :
                        Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }
    //  delete
    public boolean deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    }


