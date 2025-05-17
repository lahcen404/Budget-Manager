export interface Category {
  id?: number;
  name: string;
  limitAmount: number;
  transactions?: Transaction[];
}

export interface Transaction {
  id?: number;
  amount: number;
  date: string;
  description: string;
  category: Category;
  budget: Budget;
}

export interface Budget {
  id?: number;
  category: Category;
  limitAmount: number;
  spentAmount: number;
  transactions: Transaction[];
}
