package SmartLedger.project.elasticSearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "expenses")
public class ExpenseDocument {
    
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String userId;

    @Field(type = FieldType.Text)
    private String category;

    @Field(type = FieldType.Double)
    private Double amount;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String date;
}