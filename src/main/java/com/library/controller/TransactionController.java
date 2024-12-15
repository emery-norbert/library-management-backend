package com.library.controller;

import com.library.model.Transaction;
import com.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions
    @GetMapping
    public List<Transaction> listTransactions() {
        return transactionService.listTransactions();
    }

    // Borrow a book (create a transaction)
    @PostMapping
    public Transaction borrowBook(@RequestBody Transaction transaction) {
        return transactionService.borrowBook(transaction);
    }

    // Return a book (update a transaction)
    @PutMapping("/{id}")
    public Transaction returnBook(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.returnBook(id, transactionDetails);
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);  // Call service method to delete the transaction
            return ResponseEntity.ok().build();  // 200 OK if successful
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();  // 404 Not Found if the transaction does not exist
        }
    }
}
