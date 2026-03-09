package SmartLedger.project.elasticSearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;


/**
 * Elasticsearch document for User.
 * This class is a copy of the User model indexed in Elasticsearch for fast full-text search.
 * It is synchronized with MongoDB on every create/delete operation.
 * Note: passwordHash is intentionally excluded for security reasons.
 */
@Data
@Document(indexName = "users")
public class UserDocument {
    
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Text)
    private String email;
}
