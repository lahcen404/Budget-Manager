import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { BudgetService } from '../../../core/services/budget/budget.service';
import { CategoryService } from '../../../core/services/category/category.service';
import { Budget, Category } from '../../../core/models/budget';
import { Router } from '@angular/router';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-budget-form',
  templateUrl: './budget-form.component.html',
  imports: [
    ReactiveFormsModule,CommonModule
  ],
  styleUrls: ['./budget-form.component.css']
})
export class BudgetFormComponent implements OnInit {
  budgetForm: FormGroup;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private budgetService: BudgetService,
    private categoryService: CategoryService,
    private router: Router
  ) {
    this.budgetForm = this.fb.group({
      categoryId: ['', Validators.required],
      limitAmount: [0, [Validators.required, Validators.min(0)]],
      spentAmount: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      console.log(data)
    });
  }

  onSubmit(): void {
    if (this.budgetForm.valid) {
      const budget = {
        categoryId: this.budgetForm.value.categoryId,
        limitAmount: this.budgetForm.value.limitAmount,
        spentAmount: this.budgetForm.value.spentAmount
      };

      console.log("Sending to backend:", budget);

      this.budgetService.createBudget(budget).subscribe(() => {
        this.router.navigate(['/budgets']);
      });
    }
  }

}
