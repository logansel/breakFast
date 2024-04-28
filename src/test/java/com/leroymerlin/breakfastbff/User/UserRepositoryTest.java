package com.leroymerlin.breakfastbff.User;

import com.leroymerlin.breakfastbff.Security.BrekafastUserDetailsService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

  @InjectMocks
  private BrekafastUserDetailsService brekafastUserDetailsService;

  @Mock
  private UserRepository userRepository;

  @Test
  void findByLogin_Username() {
    // GIVEN
    UserEntity userEntity = new UserEntity("10071089", "Logan", "sel", Strings.EMPTY,
        LocalDate.of(2025, 12, 24), "logan.choupachups@gmail.com", LocalDate.of(2025, 12, 24),
        LocalDate.of(2025, 12, 24), 3, List.of(RoleEnum.ADMIN), LocalDate.of(2025, 12, 24),
        new UserEntity.Login("machi", "couli"));

    when(userRepository.findByLogin_Username("chupachups")).thenReturn(Optional.of(userEntity));
    brekafastUserDetailsService.loadUserByUsername("chupachups");

    // THEN
    verify(userRepository).findByLogin_Username("chupachups");
  }
}
