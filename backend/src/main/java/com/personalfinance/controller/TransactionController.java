package com.personalfinance.controller;

import com.personalfinance.domain.Transaction;
import com.personalfinance.domain.User;
import com.personalfinance.repository.TransactionRepository;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<Page<Transaction>> getTransactions(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    Page<Transaction> transactions = transactionRepository.findByUserOrderByTransactionDateDesc(user,
        PageRequest.of(page, size, Sort.by("transactionDate").descending()));

    return ResponseEntity.ok(transactions);
  }

  @PostMapping
  public ResponseEntity<Transaction> addTransaction(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody Transaction transaction) {

    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    transaction.setUser(user);

    Transaction saved = transactionRepository.save(transaction);
    return ResponseEntity.ok(saved);
  }
}
