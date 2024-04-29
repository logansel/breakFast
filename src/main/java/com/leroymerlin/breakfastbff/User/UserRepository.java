package com.leroymerlin.breakfastbff.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

  Optional<UserEntity> findByLogin_Username(String username);
}
