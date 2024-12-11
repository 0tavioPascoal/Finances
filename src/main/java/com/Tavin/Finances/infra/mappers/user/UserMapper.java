package com.Tavin.Finances.infra.mappers.user;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.infra.dto.usersdto.UserRequestDto;
import com.Tavin.Finances.infra.dto.usersdto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel UserModelMapper(UserRequestDto userRequestDto);

    UserResponseDto UserResponseMapper(UserModel userModel);
}
