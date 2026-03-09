package com.personalfinance.domain;

import com.personalfinance.domain.enums.Category;
import com.personalfinance.domain.enums.ColorTag;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budgets", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "user_id", "category" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @Column(nullable = false)
  private BigDecimal maximumSpend;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ColorTag colorTag;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
