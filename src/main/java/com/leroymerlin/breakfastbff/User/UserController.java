package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/all")
    public List<UserDto> getAllUser() {
        return userService.getAllUser()
                .stream()
                .map(userMapper::entityToDto)
                .toList();
    }

    @GetMapping("/{ldap}")
    public UserDto getUserByLdap(@PathVariable String ldap) {
        return userMapper.entityToDto(userService.getUserByLdap(ldap));
    }
    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.entityToDto(userService.createUser(userDto));
    }

    @PatchMapping("/{ldap}")
    public UserDto updateUserByLdap(@PathVariable String ldap, @RequestBody UserDto userDto) {
        return userMapper.entityToDto(userService.updateUserByLdap(ldap, userDto));
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
