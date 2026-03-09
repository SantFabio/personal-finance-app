package com.personalfinance.controller;

import com.personalfinance.domain.Budget;
import com.personalfinance.domain.Transaction;
import com.personalfinance.domain.User;
import com.personalfinance.dto.BudgetResponse;
import com.personalfinance.repository.BudgetRepository;
import com.personalfinance.repository.TransactionRepository;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

  private final BudgetRepository budgetRepository;
  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;

  @GetMapping
  public ResponseEntity<List<BudgetResponse>> getBudgets(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    List<Budget> budgets = budgetRepository.findByUser(user);

    List<Transaction> allTransactions = transactionRepository
        .findByUserOrderByTransactionDateDesc(user, org.springframework.data.domain.Pageable.unpaged()).getContent();

    int currentMonth = LocalDate.now().getMonthValue();
    int currentYear = LocalDate.now().getYear();

    List<BudgetResponse> response = budgets.stream().map(budget -> {

      List<Transaction> categoryTransactions = allTransactions.stream()
          .filter(t -> t.getCategory() == budget.getCategory())
          .filter(t -> t.getTransactionDate().getMonthValue() == currentMonth)
          .filter(t -> t.getTransactionDate().getYear() == currentYear)
          .toList();

      BigDecimal spent = categoryTransactions.stream()
          .map(Transaction::getAmount)
          .filter(a -> a.compareTo(BigDecimal.ZERO) < 0)
          .reduce(BigDecimal.ZERO, BigDecimal::add).abs();

      return BudgetResponse.builder()
          .id(budget.getId())
          .category(budget.getCategory())
          .maximumSpend(budget.getMaximumSpend())
          .colorTag(budget.getColorTag())
          .spent(spent)
          .remaining(budget.getMaximumSpend().subtract(spent))
          .latestSpending(categoryTransactions.stream().limit(3).collect(Collectors.toList()))
          .build();
    }).collect(Collectors.toList());

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Budget> createBudget(@AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody Budget budget) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();

    if (budgetRepository.findByUserAndCategory(user, budget.getCategory()).isPresent()) {
      return ResponseEntity.badRequest().build(); // Or throw custom exception
    }

    budget.setUser(user);
    return ResponseEntity.ok(budgetRepository.save(budget));
  }
}
