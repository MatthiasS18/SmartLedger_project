import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExpenseDto } from '../page/expenses/Expenses.component';

@Injectable({ providedIn: 'root' }) // this annotation allows to use this class "ExpenseService" in the "root"
export class ExpenseService {
  private apiUrl = '/api/expenses';

  constructor(private http: HttpClient) {}

  getAllExpenses() {
    return this.http.get<ExpenseDto[]>(`${this.apiUrl}/all`);
  }

  getExpenseById(id: string) {
    return this.http.get<ExpenseDto>(`${this.apiUrl}/${id}`);
  }

  createExpense(expense: ExpenseDto) {
    return this.http.post<ExpenseDto>(`${this.apiUrl}/create`, expense);
  }

  searchExpenses(term: string) {
    return this.http.get<ExpenseDto[]>(`${this.apiUrl}/search?term=${term}`);
  }

  deleteExpense(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}