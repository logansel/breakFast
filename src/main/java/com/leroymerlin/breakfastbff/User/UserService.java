package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getAllUser() {
        return userRepository.findAll().stream().toList();
    }

    public Optional<UserEntity> getUserByLdap(String ldap) {
        return userRepository.findById(ldap);
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity updateUserByLdap(String ldap, UserEntity userEntity) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(ldap);
        if (userEntityOpt.isPresent()) {
            userEntityOpt.get().setLdap(userEntity.getLdap());
            userEntityOpt.get().setEmail(userEntity.getEmail());
            userEntityOpt.get().setName(userEntity.getName());
            userEntityOpt.get().setSurname(userEntity.getSurname());
            return userRepository.save(userEntityOpt.get());
        } else {
            throw new UserNotFoundException("User with LDAP : " + ldap + "  was not found in the Database");
        }
    }

    public void deleteUserByLdap(String ldap) {
        UserEntity userEntityOpt = userRepository.findById(ldap).orElseThrow(() -> new UserNotFoundException("User with LDAP : " + ldap + "  was not found in the Database"));
        userRepository.deleteById(userEntityOpt.getLdap());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}

