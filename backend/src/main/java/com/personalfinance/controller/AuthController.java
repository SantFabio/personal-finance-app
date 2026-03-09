package com.personalfinance.controller;

import com.personalfinance.domain.User;
import com.personalfinance.dto.JwtResponse;
import com.personalfinance.dto.LoginRequest;
import com.personalfinance.dto.SignupRequest;
import com.personalfinance.repository.UserRepository;
import com.personalfinance.security.JwtUtils;
import com.personalfinance.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private final JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail()));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Email is already in use!");
    }

    User user = User.builder()
        .name(signUpRequest.getName())
        .email(signUpRequest.getEmail())
        .passwordHash(encoder.encode(signUpRequest.getPassword()))
        .build();

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully!");
  }
}
