import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../../../core/services/budget/budget.service';
import { Budget } from '../../../core/models/budget';
import {CommonModule} from '@angular/common';

@Component({
  imports : [CommonModule],
  selector: 'app-budget-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.css']
})
export class BudgetListComponent implements OnInit {
  budgets: Budget[] = [];

  constructor(private budgetService: BudgetService) {}

  ngOnInit(): void {
    this.loadBudgets();
  }

  loadBudgets(): void {
    this.budgetService.getBudgets().subscribe({
      next: (data) => {
        this.budgets = data;
        console.log('data :',data)
      },
      error: (err) => {
        console.error('Error fetching budgets:', err);
      }
    });
  }
}
