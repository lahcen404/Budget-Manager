import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BudgetListComponent } from './features/budget/budget-list/budget-list.component';
import {BudgetFormComponent} from './features/budget/budget-form/budget-form.component';

export const routes: Routes = [
  { path: 'budgets', component: BudgetListComponent },
  { path: 'budgets/add', component: BudgetFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutes { }
