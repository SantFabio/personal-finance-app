package com.personalfinance.security;

import com.personalfinance.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

  private UUID id;
  private String name;
  private String email;

  @JsonIgnore
  private String password;

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPasswordHash());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
