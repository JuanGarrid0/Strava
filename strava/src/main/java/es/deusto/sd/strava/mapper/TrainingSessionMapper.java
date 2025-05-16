package es.deusto.sd.strava.mapper;

import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {
    TrainingSession toEntity(TrainingSessionCreateDTO dto);
    TrainingSessionDTO toDto(TrainingSession entity);
}