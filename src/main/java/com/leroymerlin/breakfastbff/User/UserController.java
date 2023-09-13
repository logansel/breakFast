package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> userEntityList = userService.getAllUser();
        if (userEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userEntityList);
        }
    }

    @GetMapping("/{ldap}")
    public Optional<UserEntity> getUserByLdap(@PathVariable String ldap) {
        return userService.getUserByLdap(ldap);
    }

    @PostMapping("/")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @PatchMapping("/{ldap}")
    public UserEntity updateUserByLdap(@PathVariable String ldap, @RequestBody UserEntity userEntity) {
        return userService.updateUserByLdap(ldap, userEntity);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/{ldap}")
    public void deleteUserByLdap(@PathVariable String ldap) {
        userService.deleteUserByLdap(ldap);
    }
}
