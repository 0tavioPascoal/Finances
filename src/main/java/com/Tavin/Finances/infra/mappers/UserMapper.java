package com.Tavin.Finances.infra.mappers;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.infra.dto.UserRequestDto;
import com.Tavin.Finances.infra.dto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel UserModelMapper(UserRequestDto userRequestDto);

    UserResponseDto UserResponseMapper(UserModel userModel);
}
