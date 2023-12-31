package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document("user")
public class UserEntity {
    @Id
    private String ldap;
    private String name;
    private String surname;
    private String sexe;
    private String email;
    private String password;
    private LocalDateTime nextBreakFast;
    private final LocalDateTime creationDate;
    private int numberOfBreakFastOrganised;
}

