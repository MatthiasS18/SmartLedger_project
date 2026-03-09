package SmartLedger.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class allows to save the users in the db with their data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank(message = "The username is mandatory")
    @Size(min = 3, max = 30, message = "The username must contain between 3 and 30 characters")
    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String username;

    @NotBlank(message = "The email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "The password is mandatory")
    @Size(min = 8, message = "The password must contain at least 8 characters")
    private String passwordHash;

}