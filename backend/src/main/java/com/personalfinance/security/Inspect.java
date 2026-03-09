package com.personalfinance.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Inspect {
  public static void main(String[] args) {
    System.out.println("Constructors:");
    for (Constructor<?> c : DaoAuthenticationProvider.class.getConstructors()) {
      System.out.println(c);
    }
    System.out.println("Methods:");
    for (Method m : DaoAuthenticationProvider.class.getMethods()) {
      if (m.getName().toLowerCase().contains("userdetail")) {
        System.out.println(m);
      }
    }
  }
}
