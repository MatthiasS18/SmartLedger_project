package SmartLedger.project.dto;
import lombok.Data;

@Data
public class ExpenseDto {
    private String id;
    private String userId;
    private String category;
    private Double amount;
    private String description;
    private String date;
}