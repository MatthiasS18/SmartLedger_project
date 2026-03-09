import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ExpenseService } from '../../service/expense.service';
import { InvoiceService } from '../../service/invoice.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './DashBoard.component.html'
})
export class DashBoard {
  currentYear = new Date().getFullYear();

  stats = [
    { label: 'Revenue',    value: '...', trend: '',      trendUp: true,  trendLabel: 'total invoices TTC', borderClass: 'border-emerald-500/20', valueClass: 'text-emerald-400', shadowClass: 'hover:shadow-[0_0_60px_rgba(52,211,153,0.4)]' },
    { label: 'Expenses',   value: '...', trend: '',      trendUp: false, trendLabel: 'total expenses',     borderClass: 'border-rose-500/20',    valueClass: 'text-rose-400',    shadowClass: 'hover:shadow-[0_0_60px_rgba(251,113,133,0.4)]' },
    { label: 'Net Profit', value: '...', trend: '',      trendUp: true,  trendLabel: 'revenue - expenses', borderClass: 'border-blue-500/20',    valueClass: 'text-blue-400',    shadowClass: 'hover:shadow-[0_0_60px_rgba(96,165,250,0.4)]' },
  ];

  actions = [
    { icon: '👥', title: 'Users',    subtitle: 'Manage accounts', route: '/users'    },
    { icon: '💳', title: 'Expenses', subtitle: 'Track spending',  route: '/expenses' },
    { icon: '🧾', title: 'Invoices', subtitle: 'View & export',   route: '/invoices' },
  ];

  constructor(
    private expenseService: ExpenseService,
    private invoiceService: InvoiceService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    forkJoin({
      expenses: this.expenseService.getAllExpenses(),
      invoices: this.invoiceService.getAllInvoices(),
    }).subscribe(({ expenses, invoices }) => {
      const totalExpenses = expenses.reduce((sum, e) => sum + e.amount, 0);
      const totalRevenue  = invoices.reduce((sum, i) => sum + i.totalTTC, 0);
      const netProfit     = totalRevenue - totalExpenses;

      this.stats[0].value = `€${totalRevenue.toFixed(2)}`;
      this.stats[1].value = `€${totalExpenses.toFixed(2)}`;
      this.stats[2].value = `€${netProfit.toFixed(2)}`;

      this.stats[2].trendUp = netProfit >= 0;
      this.cdr.detectChanges();
    });
  }
}