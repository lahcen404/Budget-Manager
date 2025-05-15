package com.BudgetManager.BudgetManager;

import com.BudgetManager.BudgetManager.model.Budget;
import com.BudgetManager.BudgetManager.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BudgetManagerApplicationTests {

	@Test
	void testCreateBudget() {
		Category category = new Category();
		category.setId(1L);
		category.setName("Category1");
		Budget budget = new Budget();
		budget.setLimitAmount(999.9);
		budget.setSpentAmount(888.8);
		budget.setCategory(category);

		assertNotNull(budget);
		assertEquals(999.9,budget.getLimitAmount());
		assertEquals(888.8,budget.getSpentAmount());
		assertEquals("Category1",budget.getCategory().getName());

	}

}
