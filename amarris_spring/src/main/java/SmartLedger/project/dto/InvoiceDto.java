package SmartLedger.project.dto;
import lombok.Data;


/**
 * Data Transfer Object for Invoice.
 * Used to expose invoice data to the frontend without exposing the internal model.
 */
@Data
public class InvoiceDto {
    private String id;
    private String userId;
    private String invoiceNumber;
    private String issueDate;
    private String supplierName;
    private String customerName;
    private double totalTTC;
    private String currency;
}