package com.leroymerlin.breakfastbff.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if(CollectionUtils.isEmpty(userEntityList)){
            throw  new UserNotFoundException("BDD is Empty ma couille");
        }
        return userEntityList;
    }

    public UserEntity getUserByLdap(String ldap) {
         return userRepository.findById(ldap)
                 .orElseThrow(() -> new UserNotFoundException("Empty User with ldap: " +ldap));
    }

    public UserEntity createUser(UserDto userDto) {
        UserDto receive = userDto;
        receive.setCreationDate(LocalDateTime.now());
        return userRepository.save(userMapper.dtoToEntity(receive));
    }

    public UserEntity updateUserByLdap(String ldap, UserDto userDto) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(ldap);
        if (userEntityOpt.isPresent()) {
            userEntityOpt.get().setLdap(userDto.getLdap());
            userEntityOpt.get().setEmail(userDto.getEmail());
            userEntityOpt.get().setName(userDto.getName());
            userEntityOpt.get().setSurname(userDto.getSurname());
            userEntityOpt.get().setPassword(userDto.getPassword());
            userEntityOpt.get().setSexe(userDto.getSexe());
            userEntityOpt.get().setNextBreakFast(userDto.getNextBreakFast());
            userEntityOpt.get().setNumberOfBreakFastOrganised(userDto.getNumberOfBreakFastOrganised());
            return userRepository.save(userEntityOpt.get());
        } else {
            throw new UserNotFoundException("User with LDAP : " + ldap + "  was not found in the Database");
        }
    }

    public void deleteUserByLdap(String ldap) {
        UserEntity userEntityOpt = userRepository.findById(ldap).orElseThrow(() -> new UserNotFoundException("User with LDAP : " + ldap + "  was not found in the Database"));
        userRepository.deleteById(userEntityOpt.getLdap());
    }
}

