package es.deusto.sd.strava.mapper;

import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "birthDate", target = "birthdate")
    User toEntity(UserRegisterDTO dto);
    UserProfileDTO toDto(User entity);
    TokenDTO toDto(Token token);
}
