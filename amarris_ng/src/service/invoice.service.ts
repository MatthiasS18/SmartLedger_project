import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InvoiceDto } from '../page/invoices/Invoices.component';

@Injectable({ providedIn: 'root' }) // this annotation allows to use this class "InvoiceService" in the "root"
export class InvoiceService {
  private apiUrl = '/api/invoices';

  constructor(private http: HttpClient) {}

  getAllInvoices() {
    return this.http.get<InvoiceDto[]>(`${this.apiUrl}/all`);
  }

  getInvoiceById(id: string) {
    return this.http.get<InvoiceDto>(`${this.apiUrl}/${id}`);
  }

  createInvoice(invoice: InvoiceDto) {
    return this.http.post<InvoiceDto>(`${this.apiUrl}/create`, invoice);
  }

  searchInvoices(term: string) {
    return this.http.get<InvoiceDto[]>(`${this.apiUrl}/search?term=${term}`);
  }

  deleteInvoice(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}