package com.personalfinance.domain;

import com.personalfinance.domain.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String recipientSender;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private LocalDateTime transactionDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
