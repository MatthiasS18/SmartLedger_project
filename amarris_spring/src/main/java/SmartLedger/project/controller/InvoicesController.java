package SmartLedger.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SmartLedger.project.elasticSearch.document.InvoiceDocument;
import SmartLedger.project.model.Invoice;
import SmartLedger.project.service.InvoiceSERVICE;

/**
 * This class allows to provide some useful functions for the frontend.
 * It allows to handle the user
 */
@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoicesController {

   @Autowired
    private InvoiceSERVICE invoiceSERVICE;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Invoice>> getAllUsers() {
        return ResponseEntity.ok(invoiceSERVICE.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String id) {
        return invoiceSERVICE.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice saved = invoiceSERVICE.createInvoice(invoice);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    public ResponseEntity<List<InvoiceDocument>> searchInvoices(@RequestParam String term) {
        return ResponseEntity.ok(invoiceSERVICE.searchInvoices(term));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable String id) {
        invoiceSERVICE.deleteInvoice(id);
    }
    
}
