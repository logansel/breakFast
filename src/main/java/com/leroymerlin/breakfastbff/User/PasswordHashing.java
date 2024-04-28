package com.leroymerlin.breakfastbff.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashing {
  public static String passwordHashing(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }
}
