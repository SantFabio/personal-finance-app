package com.personalfinance.repository;

import com.personalfinance.domain.Transaction;
import com.personalfinance.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  Page<Transaction> findByUserOrderByTransactionDateDesc(User user, Pageable pageable);

  List<Transaction> findTop5ByUserOrderByTransactionDateDesc(User user);
}
