package com.personalfinance.dto;

import com.personalfinance.domain.Budget;
import com.personalfinance.domain.Pot;
import com.personalfinance.domain.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class DashboardResponse {
  private BigDecimal currentBalance;
  private BigDecimal income;
  private BigDecimal expenses;

  // Summaries
  private BigDecimal totalSaved;

  // Top items
  private List<Transaction> recentTransactions;
  private List<Pot> pots;
  private List<Budget> budgets;

  // Recurring Bills Overview
  private BigDecimal paidBills;
  private BigDecimal totalUpcoming;
  private BigDecimal dueSoon;
}
