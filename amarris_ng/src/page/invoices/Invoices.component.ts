import { CommonModule } from "@angular/common";
import { ChangeDetectorRef, Component, inject } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ReusableDialogComponent } from "../../components/dialog/Dialog.component";
import { InvoiceService } from "../../service/invoice.service";
import { FormsModule } from "@angular/forms";
import { InvoiceBarChart } from "../../components/bar/BarChart.component";

export interface InvoiceDto {
  id: string;
  invoiceNumber: string;
  issueDate: string;
  supplierName: string;
  customerName: string;
  totalTTC: number;
  currency: string;
}


@Component({
  selector: 'app-invoice',
  standalone: true,
  imports: [CommonModule, FormsModule, InvoiceBarChart],
  templateUrl: './Invoices.component.html'
})
export class Invoice {
  invoices: InvoiceDto[] = [];
  readonly dialog = inject(MatDialog);
  searchTerm: string = '';

  constructor(private invoiceService: InvoiceService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadInvoices();
  }

  loadInvoices() {
    this.invoiceService.getAllInvoices().subscribe(data => {
      this.invoices = data;
      this.cdr.detectChanges();
    });
  }

  openCreateInvoiceDialog(): void {
    const dialogRef = this.dialog.open(ReusableDialogComponent, {
      data: {
        title: 'Create an invoice',
        fields: [
        { label: 'Invoice Number', key: 'invoiceNumber', value: '', type: 'text' },
        { label: 'Issue Date',     key: 'issueDate',     value: '', type: 'date' },
        { label: 'Supplier Name',  key: 'supplierName',  value: '', type: 'text' },
        { label: 'Customer Name',  key: 'customerName',  value: '', type: 'text' },
        { label: 'Unit Price HT',  key: 'unitPriceHT',   value: '', type: 'number' },
        { label: 'Quantity',       key: 'quantity',      value: '', type: 'number' },
        { label: 'Vat Rate',       key: 'vatRate',       value: '', type: 'number' },
        { label: 'Currency',       key: 'currency',      value: '', type: 'select', options: ['EUR', 'USD'] },
        ],
        onConfirm: (data: any) => this.invoiceService.createInvoice(data)
      }
    });

      dialogRef.afterClosed().subscribe(result => {
          if (result) this.loadInvoices();
      });
    }

  get filteredInvoices() {
    return this.invoices;
  }

  onSearch(term: string) {
    if (term.trim() === '') {
      this.loadInvoices();
    } else {
      this.invoiceService.searchInvoices(term).subscribe(data => {
        this.invoices = data;
      });
    }
  }
}