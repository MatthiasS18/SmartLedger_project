package SmartLedger.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SmartLedger.project.db.InvoicesDB;
import SmartLedger.project.elasticSearch.document.InvoiceDocument;
import SmartLedger.project.elasticSearch.repository.InvoiceSearchRepository;
import SmartLedger.project.model.Invoice;


/**
 * This class acts as a bridge between the DB and the controller
 */
@Service
public class InvoiceSERVICE {

    @Autowired
    private InvoicesDB invoiceDB; // MongoDB

    @Autowired
    private InvoiceSearchRepository esRepository; // Elasticsearch

    public Iterable<Invoice> getAllInvoices() {
        return invoiceDB.findAll();
    }

    public Optional<Invoice> getInvoiceById(String id) {
        return invoiceDB.findById(id);
    }

    public List<Invoice> getInvoicesByUserId(String userId) {
        return invoiceDB.findAllByUserId(userId);
    }

    public List<Invoice> getInvoicesByCurrency(String currency) {
        return invoiceDB.findByCurrency(currency);
    }

    public boolean existsByCustomerName(String customerName) {
        return invoiceDB.existsByCustomerName(customerName);
    }

    public Invoice createInvoice(Invoice invoice) {
        // Calcul automatique
        invoice.setTotalHT(invoice.getUnitPriceHT() * invoice.getQuantity());
        invoice.setVatAmount(invoice.getTotalHT() * invoice.getVatRate() / 100);
        invoice.setTotalTTC(invoice.getTotalHT() + invoice.getVatAmount());

        // Sauvegarde MongoDB
        Invoice saved = invoiceDB.save(invoice);

        // Synchronise Elasticsearch
        InvoiceDocument doc = new InvoiceDocument();
        doc.setId(saved.getId());
        doc.setInvoiceNumber(saved.getInvoiceNumber());
        doc.setSupplierName(saved.getSupplierName());
        doc.setCustomerName(saved.getCustomerName());
        doc.setTotalTTC(saved.getTotalTTC());
        doc.setCurrency(saved.getCurrency());
        esRepository.save(doc);

        return saved;
    }

    public void deleteInvoice(String id) {
        invoiceDB.deleteById(id);
        esRepository.deleteById(id);
    }

    public List<InvoiceDocument> searchInvoices(String term) {
        return esRepository.findBySupplierNameContainingOrCustomerNameContaining(term, term);
    }
}