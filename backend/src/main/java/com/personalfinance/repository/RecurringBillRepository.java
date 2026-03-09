package com.personalfinance.repository;

import com.personalfinance.domain.RecurringBill;
import com.personalfinance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringBillRepository extends JpaRepository<RecurringBill, Long> {
  List<RecurringBill> findByUser(User user);
}
