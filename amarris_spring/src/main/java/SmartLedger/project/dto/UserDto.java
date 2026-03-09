package SmartLedger.project.dto;
import lombok.Data;


/**
 * Data Transfer Object for User.
 * Used to expose user data to the frontend without exposing the internal model.
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
}