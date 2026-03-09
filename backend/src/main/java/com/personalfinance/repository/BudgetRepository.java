package com.personalfinance.repository;

import com.personalfinance.domain.Budget;
import com.personalfinance.domain.User;
import com.personalfinance.domain.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
  List<Budget> findByUser(User user);

  Optional<Budget> findByUserAndCategory(User user, Category category);
}
