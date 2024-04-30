package com.leroymerlin.breakfastbff.Security;

import com.leroymerlin.breakfastbff.User.RoleEnum;
import com.leroymerlin.breakfastbff.User.UserEntity;
import com.leroymerlin.breakfastbff.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// implement l'interface UserDetailsService pour respecter le contrat avec SpringSecurity
public class BrekafastUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  // On doit récupérer via notre repository l'entité via son login et la transformer en UserDetails
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepository.findByLogin_Username(login).map(this::createSecurityUser).orElseThrow(
        () -> new UsernameNotFoundException("User with login " + login + " could not be found."));
  }

  private User createSecurityUser(UserEntity userEntity) {
    // création d'une list de SimpleGrantedAuthority pour respecter le contrat USER qui a un Login /
    // Password / List<Roles>
    List<SimpleGrantedAuthority> grantedRoles = userEntity.getRoles().stream().map(RoleEnum::name)
        .map(SimpleGrantedAuthority::new).toList();
    return new User(userEntity.getLogin().getUsername(), userEntity.getLogin().getPassword(),
        grantedRoles);
  }
}
