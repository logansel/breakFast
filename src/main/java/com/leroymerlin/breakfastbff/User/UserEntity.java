package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserEntity {
    @Id
    private String ldap;
    private String name;
    private String surname;
    private String email;
}

