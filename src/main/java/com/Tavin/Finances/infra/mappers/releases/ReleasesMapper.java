package com.Tavin.Finances.infra.mappers.releases;

import com.Tavin.Finances.entities.ReleasesModel;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesRequestDto;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesResponseDto;
import com.Tavin.Finances.infra.mappers.user.UserMapper;
import com.Tavin.Finances.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class ReleasesMapper {

    @Autowired
    UserRepository Repository;

    @Mapping(target = "user", expression = "java( Repository.findById(requestDto.idUser()).orElse(null) )")
    public abstract ReleasesModel releasesModel(ReleasesRequestDto requestDto);

    public abstract ReleasesResponseDto releasesModelResponse(ReleasesModel releasesModel);
}
