package SmartLedger.project.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class allows to save the expenses in the db with their data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "expenses")
public class Expense{

    @Id
    private String id;

    private String userId;

    private CategoryExpense category;

    @Positive
    private Double amount;

    @NotBlank
    private String description;

    private String date;

}