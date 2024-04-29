package com.leroymerlin.breakfastbff.Security;

import com.leroymerlin.breakfastbff.User.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts API")
@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AuthenticationManagerBuilder authenticationManagerBuilder;

  private final SecurityContextRepository securityContextRepository =
      new HttpSessionSecurityContextRepository();

  // Pour logout
  private final SecurityContextLogoutHandler securityContextLogoutHandler =
      new SecurityContextLogoutHandler();

  @PostMapping("/login")
  public void login(@RequestBody @Valid UserEntity.Login credentials, HttpServletRequest request,
      HttpServletResponse response) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(credentials.getUsername(),
            credentials.getPassword());
    Authentication authentication =
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    securityContextRepository.saveContext(securityContext, request, response);
  }

  @GetMapping("/logout")
  public void logout(Authentication authentication, HttpServletRequest request,
      HttpServletResponse response) {
    securityContextLogoutHandler.logout(request, response, authentication);
  }

}
