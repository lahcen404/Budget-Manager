import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../services/transaction.service';
import {NgClass} from '@angular/common';
import {TransactionFormComponent} from '../transaction-form/transaction-form.component';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  standalone: true,

  imports: [
    CommonModule,
    NgClass,
    TransactionFormComponent,
    FormsModule


  ],
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {
  transactions: any[] = [];
  filteredTransactions: any[] = [];
  selectedTransaction: any = null;
  showForm = false;

  searchText = '';
  dateFilter: string = '';
  sortField: string = 'date';
  sortDirection: string = 'desc';

  constructor(public transactionService: TransactionService) { }

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionService.loadTransactions().subscribe(
      (data) => {
        this.transactions = data;
        this.transactionService.transactions = data;
        this.applyFilters();
      },
      (error) => {
        console.error('Erreur lors du chargement des transactions', error);
      }
    );
  }

  applyFilters(): void {
    let filtered = [...this.transactions];

    if (this.searchText) {
      const searchLower = this.searchText.toLowerCase();
      filtered = filtered.filter(t =>
        t.description.toLowerCase().includes(searchLower) ||
        (t.category?.name && t.category.name.toLowerCase().includes(searchLower))
      );
    }

    if (this.dateFilter) {
      filtered = filtered.filter(t => t.date === this.dateFilter);
    }

    filtered.sort((a, b) => {
      let comparison = 0;
      if (this.sortField === 'date') {
        comparison = new Date(a.date).getTime() - new Date(b.date).getTime();
      } else if (this.sortField === 'amount') {
        comparison = a.amount - b.amount;
      }

      return this.sortDirection === 'asc' ? comparison : -comparison;
    });

    this.filteredTransactions = filtered;
  }

  sortBy(field: string): void {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'desc';
    }
    this.applyFilters();
  }

  getSortIcon(field: string): string {
    if (this.sortField !== field) return 'bi bi-chevron-expand';
    return this.sortDirection === 'asc' ? 'bi bi-chevron-up' : 'bi bi-chevron-down';
  }

  resetFilters(): void {
    this.searchText = '';
    this.dateFilter = '';
    this.applyFilters();
  }

  addTransaction(): void {
    this.selectedTransaction = null;
    this.showForm = true;
  }

  editTransaction(transaction: any): void {
    this.selectedTransaction = { ...transaction };
    this.showForm = true;
  }

  deleteTransaction(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette transaction ?')) {
      this.transactionService.deleteTransaction(id).subscribe(
        () => this.loadTransactions(),
        (error) => console.error('Erreur suppression', error)
      );
    }
  }

  handleTransactionSubmit(transaction: any): void {
    if (transaction.id) {
      this.transactionService.updateTransaction(transaction.id, transaction).subscribe(
        () => {
          this.loadTransactions();
          this.cancelForm();
        },
        (error) => console.error('Erreur mise à jour', error)
      );
    } else {
      this.transactionService.addTransaction(transaction).subscribe(
        () => this.loadTransactions(),
        (error) => console.error('Erreur ajout', error)
      );
    }
  }

  cancelForm(): void {
    this.showForm = false;
    this.selectedTransaction = null;
  }

  getTotalBalance(): number {
    return this.transactionService.getTotalBalance();
  }

  formatAmount(amount: number): string {
    return this.transactionService.formatAmount(amount);
  }

  getAmountClass(amount: number): string {
    return this.transactionService.getAmountClass(amount);
  }
}
