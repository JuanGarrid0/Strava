package es.deusto.sd.strava.mapper;

import es.deusto.sd.strava.dto.ChallengeCreateDTO;
import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.UserChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {

    Challenge toEntity(ChallengeCreateDTO dto);

    ChallengeDTO toDto(Challenge entity);

    @Mapping(source = "acceptance.challenge.name", target = "name")
    @Mapping(source = "acceptance.challenge.sport", target = "sport")
    @Mapping(source = "acceptance.challenge.startDate", target = "startDate")
    @Mapping(source = "acceptance.challenge.endDate", target = "endDate")
    @Mapping(source = "acceptance.challenge.objectiveValue", target = "objectiveValue")
    @Mapping(target = "progressPercent", ignore = true)
    UserChallengeDTO toUserChallengeDto(ChallengeAcceptance acceptance);
}