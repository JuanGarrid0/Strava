package es.deusto.sd.strava.mapper;

import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    Challenge toEntity(ChallengeCreateDTO dto);
    ChallengeDTO toDto(Challenge entity);
    @Mapping(source = "challenge.id", target = "id")
    UserChallengeDTO toUserChallengeDto(ChallengeAcceptance acceptance);
}

