package com.library.service;

import com.library.model.Transaction;
import com.library.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> listTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction borrowBook(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction returnBook(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setReturnDate(transactionDetails.getReturnDate());
        transaction.setStatus("returned");
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
