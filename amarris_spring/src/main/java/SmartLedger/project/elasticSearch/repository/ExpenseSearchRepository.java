package SmartLedger.project.elasticSearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import SmartLedger.project.elasticSearch.document.ExpenseDocument;


/**
 * Elasticsearch repository for Expense.
 * Provides full-text search capabilities on expenses.
 * Used by ExpenseSERVICE to search expenses by description or category.
 */
public interface ExpenseSearchRepository extends ElasticsearchRepository<ExpenseDocument, String> {
    
   List<ExpenseDocument> findByDescriptionContainingOrCategoryContaining(
        String description, String category
    );
}
