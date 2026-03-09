package SmartLedger.project.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class allows to save the invoices in the db with their data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String invoiceNumber;

    private String issueDate;

    private String operationDate;

    @NotBlank
    private String supplierName;

    private String supplierAddress;      // optionnel
    private String supplierVatNumber;    // optionnel

    @NotBlank
    private String customerName;

    private String customerAddress;      // optionnel
    private String customerVatNumber;    // optionnel

    @Positive
    private double unitPriceHT;

    @Positive
    private double quantity;

    private double totalHT;              // calculé automatiquement
    private double vatRate;
    private double vatAmount;            // calculé automatiquement
    private double totalTTC;             // calculé automatiquement

    private String currency;
    private String specialMention;
}