package SmartLedger.project.elasticSearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;


/**
 * Elasticsearch document for Invoice.
 * This class is a copy of the Invoice model indexed in Elasticsearch for fast full-text search.
 * It is synchronized with MongoDB on every create/delete operation.
 */
@Data
@Document(indexName = "invoices")
public class InvoiceDocument {
    
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String invoiceNumber;

    @Field(type = FieldType.Text)
    private String supplierName;

    @Field(type = FieldType.Text)
    private String customerName;

    @Field(type = FieldType.Text)
    private String currency;

    @Field(type = FieldType.Double)
    private double totalTTC;
}