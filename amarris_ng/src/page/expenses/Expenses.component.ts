import { CommonModule } from "@angular/common";
import { ChangeDetectorRef, Component, inject } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ReusableDialogComponent } from "../../components/dialog/Dialog.component";
import { ExpenseService } from "../../service/expense.service";
import { FormsModule } from "@angular/forms";
import { DonutChart } from "../../components/donuts/DonutChart.component";

export interface ExpenseDto {
  id: string;
  userId: string;
  category: string;
  amount: number;
  description: string;
  date: string;
}

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [CommonModule, FormsModule, DonutChart],
  templateUrl: './Expenses.component.html'
})
export class Expense {
  expenses: ExpenseDto[] = [];
  readonly dialog = inject(MatDialog);
  searchTerm: string = '';

  constructor(private expenseService: ExpenseService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadExpenses();
  }

  loadExpenses() {
    this.expenseService.getAllExpenses().subscribe(data => {
    this.expenses = data;
    this.cdr.detectChanges();
    });
  }

  openCreateExpenseDialog(): void {
    const dialogRef = this.dialog.open(ReusableDialogComponent, {
      data: {
        title: 'Create an expense',
        fields: [
          { label: 'Description', key: 'description', value: '', type: 'text' },
          { label: 'Amount',      key: 'amount',      value: '', type: 'number' },
          { label: 'Date',        key: 'date',        value: '', type: 'date' },
          { label: 'Category',    key: 'category',    value: '', type: 'select', options: ['TRANSPORT', 'SUBSCRIPTION', 'MARKETING', 'SOFTWARE', 'MATERIAL'] },
        ],
        onConfirm: (data: any) => this.expenseService.createExpense(data)
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadExpenses();
    });
  }


  get filteredInvoices() {
    return this.expenses;
  }

  onSearch(term: string) {
    if (term.trim() === '') {
      this.loadExpenses();
    } else {
      this.expenseService.searchExpenses(term).subscribe(data => {
        this.expenses = data;
      });
    }
  }

  deleteExpense(id: string) {
    this.expenseService.deleteExpense(id).subscribe(() => {
      this.expenses = this.expenses.filter(exp => exp.id !== id);
      this.loadExpenses();
    });
  }
}

