import {Component, OnInit} from '@angular/core';
import {Transaction} from '../../../core/models/budget';
import {TransactionService} from '../../../core/services/transactions/transaction.service';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-transaction-list',
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css'
})
export class TransactionListComponent implements  OnInit{

  transactions: Transaction[]= [];

  constructor(private transactionService: TransactionService){}

  ngOnInit() {
    this.loadTransactions();
  }

   loadTransactions() {
    this.transactionService.getTransactions().subscribe({
      next: value => (data: Transaction[]) => {
        this.transactions= data;
        console.log("data transactionss" , data)
      }
    })
  }
}
