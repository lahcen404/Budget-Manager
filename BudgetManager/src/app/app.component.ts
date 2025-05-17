import { Component } from '@angular/core';
import {NavbarComponent} from './navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {TransactionListComponent} from './transaction-list/transaction-list.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,

  imports: [NavbarComponent, HomeComponent, TransactionListComponent, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'BudgetManagerFrontEnd';
}
