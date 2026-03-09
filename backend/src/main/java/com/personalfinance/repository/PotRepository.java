package com.personalfinance.repository;

import com.personalfinance.domain.Pot;
import com.personalfinance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotRepository extends JpaRepository<Pot, Long> {
  List<Pot> findByUser(User user);
}
