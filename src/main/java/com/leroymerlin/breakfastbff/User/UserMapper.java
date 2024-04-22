package com.leroymerlin.breakfastbff.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserEntity dtoToEntity(UserDto userDto);

  UserDto entityToDto(UserEntity userEntity);
}

