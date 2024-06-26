package com.leroymerlin.breakfastbff.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @Id
  @NotBlank(message = "The LDAP must be defined.")
  private String ldap;
  @NotBlank
  private String firstname;
  @NotBlank
  private String lastname;
  @NotBlank
  private String picture;
  @NotBlank
  private String email;
  private LocalDate lastOrganizedBreakfastDate;
  private LocalDate nextOrganizedBreakfastDate;
  private int numberOfBreakFastOrganised;
  private List<RoleEnum> roles;
  private LocalDate creationDate;
  private UserEntity.Login login;


  @Getter
  @AllArgsConstructor
  public static class Login implements Serializable {
    private String username;
    private String password;
  }
}
