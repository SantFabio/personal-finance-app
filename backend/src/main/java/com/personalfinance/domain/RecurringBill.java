package com.personalfinance.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringBill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private Integer dueDayOfMonth;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
