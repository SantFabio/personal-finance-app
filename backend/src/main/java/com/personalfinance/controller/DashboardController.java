package com.personalfinance.controller;

import com.personalfinance.domain.*;
import com.personalfinance.dto.DashboardResponse;
import com.personalfinance.repository.*;
import com.personalfinance.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/overview")
@RequiredArgsConstructor
public class DashboardController {

  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final BudgetRepository budgetRepository;
  private final PotRepository potRepository;
  private final RecurringBillRepository recurringBillRepository;

  @GetMapping
  public ResponseEntity<DashboardResponse> getOverview(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();

    List<Transaction> allTransactions = transactionRepository
        .findByUserOrderByTransactionDateDesc(user, org.springframework.data.domain.Pageable.unpaged()).getContent();

    BigDecimal income = allTransactions.stream()
        .map(Transaction::getAmount)
        .filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal expenses = allTransactions.stream()
        .map(Transaction::getAmount)
        .filter(a -> a.compareTo(BigDecimal.ZERO) < 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add).abs();

    List<Pot> pots = potRepository.findByUser(user);
    BigDecimal totalSaved = pots.stream()
        .map(Pot::getTotalSaved)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal currentBalance = income.subtract(expenses).subtract(totalSaved);

    List<Transaction> recentTransactions = transactionRepository.findTop5ByUserOrderByTransactionDateDesc(user);
    List<Budget> budgets = budgetRepository.findByUser(user);
    List<RecurringBill> bills = recurringBillRepository.findByUser(user);

    // Simple logic for bills due soon vs upcoming
    int currentDay = LocalDate.now().getDayOfMonth();
    BigDecimal paidBills = BigDecimal.ZERO; // Simplified, in a real scenario requires mapping bills to transactions
    BigDecimal dueSoon = bills.stream()
        .filter(b -> b.getDueDayOfMonth() >= currentDay && b.getDueDayOfMonth() <= currentDay + 7)
        .map(RecurringBill::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal totalUpcoming = bills.stream()
        .filter(b -> b.getDueDayOfMonth() > currentDay)
        .map(RecurringBill::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    DashboardResponse response = DashboardResponse.builder()
        .currentBalance(currentBalance)
        .income(income)
        .expenses(expenses)
        .totalSaved(totalSaved)
        .pots(pots)
        .budgets(budgets)
        .recentTransactions(recentTransactions)
        .paidBills(paidBills)
        .totalUpcoming(totalUpcoming)
        .dueSoon(dueSoon)
        .build();

    return ResponseEntity.ok(response);
  }
}
