import { Routes } from '@angular/router';
import { DashBoard } from '../page/dashBoard/DashBoard.component';
import { User } from '../page/users/User.component';
import { Invoice } from '../page/invoices/Invoices.component';
import { Expense } from '../page/expenses/Expenses.component';

export const routes: Routes = [
  { path: '',          redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashBoard },
  { path: 'users',     component: User },
  { path: 'expenses',  component: Expense },
  { path: 'invoices',  component: Invoice },
  { path: '**',        redirectTo: 'dashboard' },
];
