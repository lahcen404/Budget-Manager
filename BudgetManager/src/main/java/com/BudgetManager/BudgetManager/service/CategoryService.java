package com.BudgetManager.BudgetManager.service;

import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.model.Category;
import com.BudgetManager.BudgetManager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }




    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(categoryDetails.getName());
        category.setLimitAmount(categoryDetails.getLimitAmount());
        category.setTransactions(category.getTransactions());


        return categoryRepository.save(category);

    }

}
