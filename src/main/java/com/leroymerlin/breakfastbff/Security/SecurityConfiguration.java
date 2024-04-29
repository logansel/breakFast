package com.leroymerlin.breakfastbff.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private BrekafastUserDetailsService brekafastUserDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(this.brekafastUserDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    return new ProviderManager(authenticationProvider);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .headers(
            headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                .permissionsPolicy(permissionsPolicyConfig -> permissionsPolicyConfig.policy(
                    "fullscreen=(self), geolocation=(), microphone=(), camera=()")))
        .authorizeHttpRequests(
            authorizations -> authorizations
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/accounts/login").permitAll()
                .requestMatchers("/healthcheck").permitAll()
                .requestMatchers(HttpMethod.POST, "users/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "users/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "users/**").hasAuthority("ADMIN").anyRequest()
                .authenticated());
    return http.build();
  }
}
