package SmartLedger.project.db;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import SmartLedger.project.model.Expense;

/**
 * This class allows to handle the expenses with many functions already present in MongoRepository
 */
@Repository
public interface ExpensesDB extends MongoRepository<Expense, String> {

    Optional<Expense> findById(String id);

}