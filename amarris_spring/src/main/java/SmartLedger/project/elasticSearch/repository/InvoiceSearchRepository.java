package SmartLedger.project.elasticSearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import SmartLedger.project.elasticSearch.document.InvoiceDocument;


/**
 * Elasticsearch repository for Invoice.
 * Provides full-text search capabilities on invoices.
 * Used by InvoiceSERVICE to search invoices by supplier name or customer name.
 */
public interface InvoiceSearchRepository extends ElasticsearchRepository<InvoiceDocument, String> {

    List<InvoiceDocument> findBySupplierNameContainingOrCustomerNameContaining(
        String supplierName, String customerName
    );
}
