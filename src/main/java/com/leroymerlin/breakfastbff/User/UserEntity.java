package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document("user")
public class UserEntity {
  @Id
  private String ldap;
  private String firstName;
  private String lastName;
  private String picture;
  private LocalDate nextBreakFast;
  private String email;
  private LocalDate lastOrganizedBreakfastDate;
  private LocalDate nextOrganizedBreakfastDate;
  private int numberOfBreakFastOrganised;
  private List<RoleEnum> role;
  private final LocalDate creationDate;
  private Login login;


  @Getter
  @AllArgsConstructor
  public static class Login implements Serializable {
    private String username;
    private String password;
  }
}
