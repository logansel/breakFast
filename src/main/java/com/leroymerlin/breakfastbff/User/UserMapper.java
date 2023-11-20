package com.leroymerlin.breakfastbff.User;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserDto userDto);
    List<UserEntity> dtoListToEntityList(List<UserDto> userDtoList);
    UserDto entityToDto(UserEntity userEntity);
    List<UserDto> entityListToDtoList(List<UserEntity> userEntity);
}

