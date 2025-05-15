package com.BudgetManager.BudgetManager.mapper;

import com.BudgetManager.BudgetManager.dto.TransactionDTO;
import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.model.Category;
import com.BudgetManager.BudgetManager.model.Transaction;
import com.BudgetManager.BudgetManager.repository.BudgetRepository;
import com.BudgetManager.BudgetManager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private BudgetRepository budgetRepository;


  public TransactionDTO toDTO(Transaction transaction) {
    TransactionDTO dto = new TransactionDTO();
    dto.setId(transaction.getId());
    dto.setAmount(transaction.getAmount());
    dto.setDate(transaction.getDate());
    dto.setDescription(transaction.getDescription());

    if (transaction.getCategory() != null) {
      dto.setCategoryId(transaction.getCategory().getId());
    }


    if (transaction.getBudget() != null) {
      dto.setBudgetId(transaction.getBudget().getId());
    }

    return dto;
  }


  public Transaction toEntity(TransactionDTO dto) {
    Transaction transaction = new Transaction();

     if (dto.getId() != null) {
      transaction.setId(dto.getId());
    }

    transaction.setAmount(dto.getAmount());
    transaction.setDate(dto.getDate());
    transaction.setDescription(dto.getDescription());

     if (dto.getCategoryId() != null) {
      Category category = categoryRepository.findById(dto.getCategoryId())
        .orElse(null);
      transaction.setCategory(category);
    }
     if (dto.getBudgetId() != null) {
      Budget budget = budgetRepository.findById(dto.getBudgetId())
        .orElse(null);
      transaction.setBudget(budget);
    }

    return transaction;
  }


  public List<TransactionDTO> toDTOList(List<Transaction> transactions) {
    return transactions.stream()
      .map(this::toDTO)
      .collect(Collectors.toList());
  }
}
