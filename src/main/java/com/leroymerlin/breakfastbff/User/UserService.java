package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.leroymerlin.breakfastbff.User.PasswordHashing.passwordHashing;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Page<UserEntity> getAllUser(Pageable pageable) {
    Page<UserEntity> userEntityList = userRepository.findAll(pageable);
    if (userEntityList.isEmpty()) {
      throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "BDD is Empty");
    }
    return userEntityList;
  }

  public UserEntity getUserByLdap(String ldap) {
    return userRepository.findById(ldap).orElseThrow(
        () -> new UserNotFoundException(HttpStatus.BAD_REQUEST, "Empty User with ldap: " + ldap));
  }

  public UserEntity createUser(UserDto userDto) {
    if (userDto == null) {
      throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "UserDto is null");
    } else {
      userDto.setRole(List.of(RoleEnum.USER));
      userDto.setCreationDate(LocalDateTime.now().toLocalDate());
      userDto.setLogin(new UserEntity.Login(userDto.getLogin().getPassword(),
          passwordHashing(userDto.getLogin().getPassword())));
      return userRepository.save(userMapper.dtoToEntity(userDto));
    }
  }

  public UserEntity updateUserByLdap(String ldap, UserDto userDto) {
    UserEntity userEntity = userRepository.findById(ldap)
        .orElseThrow(() -> new UserNotFoundException(HttpStatus.BAD_REQUEST,
            "User with LDAP : " + ldap + "  was not found in the Database"));

    userEntity.setFirstName(userDto.getFirstName());
    userEntity.setLastName(userDto.getLastName());
    userEntity.setPicture(userDto.getPicture());
    userEntity.setNextBreakFast(userDto.getNextBreakFast());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setLastOrganizedBreakfastDate(userDto.getLastOrganizedBreakfastDate());
    userEntity.setNextOrganizedBreakfastDate(userDto.getNextOrganizedBreakfastDate());
    userEntity.setNumberOfBreakFastOrganised(userDto.getNumberOfBreakFastOrganised());
    return userRepository.save(userEntity);
  }

  public void deleteUserByLdap(String ldap) {
    UserEntity userEntityOpt = userRepository.findById(ldap)
        .orElseThrow(() -> new UserNotFoundException(HttpStatus.BAD_REQUEST,
            "User with LDAP : " + ldap + "  was not found in the Database"));
    userRepository.deleteById(userEntityOpt.getLdap());
  }

  public void deleteAllUsers() {
    userRepository.deleteAll();
  }
}

