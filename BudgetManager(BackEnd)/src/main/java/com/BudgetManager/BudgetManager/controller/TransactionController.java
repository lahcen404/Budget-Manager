package com.BudgetManager.BudgetManager.controller;

import com.BudgetManager.BudgetManager.model.Transaction;
import com.BudgetManager.BudgetManager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*") // autoriser les requêtes CORS si besoin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // l'ajoute
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    // Récupération
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }


    //  Modifier
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Optional<Transaction> updated = transactionService.updateTransaction(id, updatedTransaction);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionService.deleteTransaction(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 🔍 Filtrer par date
    @GetMapping("/filter/by-date")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(transactionService.getTransactionsByDate(date));
    }

    // 🔃 Trier par montant
    @GetMapping("/sort/by-amount")
    public ResponseEntity<List<Transaction>> getTransactionsSortedByAmount(@RequestParam(defaultValue = "true") boolean ascending) {
        return ResponseEntity.ok(transactionService.getTransactionsSortedByAmount(ascending));
    }

    // 🔃 Trier par date
    @GetMapping("/sort/by-date")
    public ResponseEntity<List<Transaction>> getTransactionsSortedByDate(@RequestParam(defaultValue = "true") boolean ascending) {
        return ResponseEntity.ok(transactionService.getTransactionsSortedByDate(ascending));
    }
}

