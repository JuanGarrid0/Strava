package es.deusto.sd.strava.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import es.deusto.sd.strava.dto.ChallengeCreateDTO;
import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.UserChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    Challenge toEntity(ChallengeCreateDTO dto);
    ChallengeDTO toDto(Challenge entity);
    @Mapping(source = "challenge.id", target = "id")
    UserChallengeDTO toUserChallengeDto(ChallengeAcceptance acceptance);
}
