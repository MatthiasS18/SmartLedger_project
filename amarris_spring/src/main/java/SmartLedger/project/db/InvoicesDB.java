package SmartLedger.project.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import SmartLedger.project.model.Invoice;

/**
 * This class allows to handle the invoices with many functions already present in MongoRepository
 */
@Repository
public interface InvoicesDB extends MongoRepository<Invoice, String> {

    Optional<Invoice> findById(String id);

    List<Invoice> findAllByUserId(String userId);

    List<Invoice> findByCurrency(String currency);

    boolean existsByCustomerName(String customerName);
}