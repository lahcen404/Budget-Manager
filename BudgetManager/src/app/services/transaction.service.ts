import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  apiUrl = 'http://localhost:8080/api/transactions';
  transactions: any[] = [];

  constructor(private http: HttpClient) {}

  loadTransactions(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  addTransaction(transaction: any): Observable<any> {
    return this.http.post(this.apiUrl, transaction);
  }

  updateTransaction(id: number, transaction: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, transaction);
  }

  deleteTransaction(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getTotalBalance(): number {
    return this.transactions.reduce((sum, t) => sum + t.amount, 0);
  }

  formatAmount(amount: number): string {
    const sign = amount >= 0 ? '+' : '-';
    return `${sign} ${Math.abs(amount).toFixed(2)} €`;
  }

  getAmountClass(amount: number): string {
    return amount >= 0 ? 'text-success' : 'text-danger';
  }
}
