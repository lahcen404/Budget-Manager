package com.BudgetManager.BudgetManager.controller;

import com.BudgetManager.BudgetManager.dto.TransactionDTO;
import com.BudgetManager.BudgetManager.mapper.TransactionMapper;
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
@CrossOrigin(origins = "http://localhost:4200")

public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private TransactionMapper transactionMapper;


   // Add

  @PostMapping
  public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
    Transaction transaction = transactionMapper.toEntity(transactionDTO);
    Transaction savedTransaction = transactionService.addTransaction(transaction);
    return ResponseEntity.ok(transactionMapper.toDTO(savedTransaction));
  }


   //Get all

  @GetMapping
  public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
    List<Transaction> transactions = transactionService.getAllTransactions();
    return ResponseEntity.ok(transactionMapper.toDTOList(transactions));
  }

   // Update

  @PutMapping("/{id}")
  public ResponseEntity<TransactionDTO> updateTransaction(
    @PathVariable Long id,
    @RequestBody TransactionDTO transactionDTO) {
    Transaction transaction = transactionMapper.toEntity(transactionDTO);
    Optional<Transaction> updated = transactionService.updateTransaction(id, transaction);

    return updated.map(t -> ResponseEntity.ok(transactionMapper.toDTO(t)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

   // Delete

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
    if (transactionService.deleteTransaction(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

   // Filter transactions by date
  @GetMapping("/filter/by-date")
  public ResponseEntity<List<TransactionDTO>> getTransactionsByDate(
    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<Transaction> transactions = transactionService.getTransactionsByDate(date);
    return ResponseEntity.ok(transactionMapper.toDTOList(transactions));
  }


   // Sort transactions by amount
  @GetMapping("/sort/by-amount")
  public ResponseEntity<List<TransactionDTO>> getTransactionsSortedByAmount(
    @RequestParam(defaultValue = "true") boolean ascending) {
    List<Transaction> transactions = transactionService.getTransactionsSortedByAmount(ascending);
    return ResponseEntity.ok(transactionMapper.toDTOList(transactions));
  }


  //  Sort transactions by date

  @GetMapping("/sort/by-date")
  public ResponseEntity<List<TransactionDTO>> getTransactionsSortedByDate(
    @RequestParam(defaultValue = "true") boolean ascending) {
    List<Transaction> transactions = transactionService.getTransactionsSortedByDate(ascending);
    return ResponseEntity.ok(transactionMapper.toDTOList(transactions));
  }
}
