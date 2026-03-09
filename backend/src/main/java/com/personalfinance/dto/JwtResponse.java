package com.personalfinance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
  private String token;
  private UUID id;
  private String name;
  private String email;
}
