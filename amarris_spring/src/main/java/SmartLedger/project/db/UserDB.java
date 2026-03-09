package SmartLedger.project.db;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import SmartLedger.project.model.User;

/**
 * This class allows to handle the users with many functions already present in JpaRepository
 */
@Repository
public interface UserDB extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}