package com.personalfinance.dto;

import com.personalfinance.domain.Transaction;
import com.personalfinance.domain.enums.Category;
import com.personalfinance.domain.enums.ColorTag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class BudgetResponse {
  private Long id;
  private Category category;
  private BigDecimal maximumSpend;
  private ColorTag colorTag;

  // Aggregated data
  private BigDecimal spent;
  private BigDecimal remaining;

  private List<Transaction> latestSpending;
}
