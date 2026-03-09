package SmartLedger.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SmartLedger.project.db.ExpensesDB;
import SmartLedger.project.elasticSearch.document.ExpenseDocument;
import SmartLedger.project.elasticSearch.repository.ExpenseSearchRepository;
import SmartLedger.project.model.Expense;

/**
 * This class acts as a bridge between the DB and the controller
 */
@Service
public class ExpenseSERVICE {

    @Autowired
    private ExpensesDB expenseDB; // MongoDB

    @Autowired
    private ExpenseSearchRepository esRepository; // Elasticsearch

    public Iterable<Expense> getAllExpenses() {
        return expenseDB.findAll();
    }

    public Optional<Expense> getExpenseById(String id) {
        return expenseDB.findById(id);
    }

    public Expense createExpense(Expense expense) {
        // Sauvegarde MongoDB
        Expense saved = expenseDB.save(expense);

        // Synchronise Elasticsearch
        ExpenseDocument doc = new ExpenseDocument();
        doc.setId(saved.getId());
        doc.setUserId(saved.getUserId());
        doc.setCategory(saved.getCategory() != null ? saved.getCategory().name() : null);
        doc.setAmount(saved.getAmount());
        doc.setDescription(saved.getDescription());
        doc.setDate(saved.getDate());
        esRepository.save(doc);

        return saved;
    }

    public void deleteExpense(String id) {
        expenseDB.deleteById(id);
        esRepository.deleteById(id);
    }

    public List<ExpenseDocument> searchExpenses(String term) {
        return esRepository.findByDescriptionContainingOrCategoryContaining(term, term);
    }
}