package com.personalfinance.domain;

import com.personalfinance.domain.enums.ColorTag;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal targetAmount;

  @Column(nullable = false)
  @Builder.Default
  private BigDecimal totalSaved = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ColorTag colorTag;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
