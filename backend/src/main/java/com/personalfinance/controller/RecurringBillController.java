package com.personalfinance.controller;

import com.personalfinance.domain.RecurringBill;
import com.personalfinance.domain.User;
import com.personalfinance.repository.RecurringBillRepository;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-bills")
@RequiredArgsConstructor
public class RecurringBillController {

  private final RecurringBillRepository recurringBillRepository;
  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<RecurringBill>> getRecurringBills(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    return ResponseEntity.ok(recurringBillRepository.findByUser(user));
  }

  @PostMapping
  public ResponseEntity<RecurringBill> createRecurringBill(@AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody RecurringBill recurringBill) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    recurringBill.setUser(user);
    return ResponseEntity.ok(recurringBillRepository.save(recurringBill));
  }
}
