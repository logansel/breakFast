package com.leroymerlin.breakfastbff.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/users")
@Tag(name = "User")
@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Operation(summary = "Returns all users in BDD", description = "Returns all users in BDD")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All users returned successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserEntity.class))}),
      @ApiResponse(responseCode = "400", description = "Any users exists")})
  @GetMapping("/all")
  public Page<UserDto> getAllUser(Pageable pageable) {
    return userService.getAllUser(pageable).map(userMapper::entityToDto);
  }

  @Operation(summary = "Returns user by LDAP", description = "Returns user by LDAP")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User returned successfully", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserEntity.class))}),
      @ApiResponse(responseCode = "400", description = "Any user exists with this LDAP")})
  @GetMapping("/{ldap}")
  public UserDto getUserByLdap(@PathVariable String ldap) {
    return userMapper.entityToDto(userService.getUserByLdap(ldap));
  }

  @PostMapping
  public UserDto createUser(@Valid @RequestBody UserDto userDto) {
    return userMapper.entityToDto(userService.createUser(userDto));
  }

  @PatchMapping("/{ldap}")
  public UserDto updateUserByLdap(@PathVariable String ldap, @Valid @RequestBody UserDto userDto) {
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
