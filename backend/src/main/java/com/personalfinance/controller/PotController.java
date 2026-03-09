package com.personalfinance.controller;

import com.personalfinance.domain.Pot;
import com.personalfinance.domain.Transaction;
import com.personalfinance.domain.User;
import com.personalfinance.domain.enums.Category;
import com.personalfinance.repository.PotRepository;
import com.personalfinance.repository.TransactionRepository;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pots")
@RequiredArgsConstructor
public class PotController {

  private final PotRepository potRepository;
  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;

  @GetMapping
  public ResponseEntity<List<Pot>> getPots(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    return ResponseEntity.ok(potRepository.findByUser(user));
  }

  @PostMapping
  public ResponseEntity<Pot> createPot(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody Pot pot) {
    User user = userRepository.findById(userDetails.getId()).orElseThrow();
    pot.setUser(user);
    pot.setTotalSaved(BigDecimal.ZERO);
    return ResponseEntity.ok(potRepository.save(pot));
  }

  @PostMapping("/{id}/add-money")
  @Transactional
  public ResponseEntity<Pot> addMoney(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id,
      @RequestBody BigDecimal amount) {
    Pot pot = potRepository.findById(id).orElseThrow();
    pot.setTotalSaved(pot.getTotalSaved().add(amount));

    // As per requirements: deducting via a transaction record or direct math.
    // Creating a logging transaction.
    Transaction t = Transaction.builder()
        .user(pot.getUser())
        .recipientSender("Transfer to " + pot.getName() + " Pot")
        .amount(amount.negate())
        .category(Category.GENERAL)
        .transactionDate(LocalDateTime.now())
        .build();
    transactionRepository.save(t);

    return ResponseEntity.ok(potRepository.save(pot));
  }

  @PostMapping("/{id}/withdraw")
  @Transactional
  public ResponseEntity<Pot> withdraw(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id,
      @RequestBody BigDecimal amount) {
    Pot pot = potRepository.findById(id).orElseThrow();

    if (pot.getTotalSaved().compareTo(amount) < 0) {
      return ResponseEntity.badRequest().build();
    }

    pot.setTotalSaved(pot.getTotalSaved().subtract(amount));

    Transaction t = Transaction.builder()
        .user(pot.getUser())
        .recipientSender("Withdraw from " + pot.getName() + " Pot")
        .amount(amount)
        .category(Category.GENERAL)
        .transactionDate(LocalDateTime.now())
        .build();
    transactionRepository.save(t);

    return ResponseEntity.ok(potRepository.save(pot));
  }
}
