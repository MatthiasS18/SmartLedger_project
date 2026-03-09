package SmartLedger.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SmartLedger.project.elasticSearch.document.ExpenseDocument;
import SmartLedger.project.model.Expense;
import SmartLedger.project.service.ExpenseSERVICE;


/**
 * This class allows to provide some useful functions for the frontend.
 * It allows to handle the user
 */
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpensesController {

   @Autowired
    private ExpenseSERVICE expenseSERVICE;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseSERVICE.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String id) {
        return expenseSERVICE.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense saved = expenseSERVICE.createExpense(expense);
        return ResponseEntity.ok(saved);
    }
    
  @GetMapping("/search")
    public ResponseEntity<List<ExpenseDocument>> searchExpenses(@RequestParam String term) {
        return ResponseEntity.ok(expenseSERVICE.searchExpenses(term));
    }
    
}
