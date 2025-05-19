package es.deusto.sd.strava.mapper;

import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {
    /** Convierte DTO de creación a entidad. */
    TrainingSession toEntity(TrainingSessionCreateDTO dto);

    /** Convierte entidad a DTO de respuesta. */
    TrainingSessionDTO toDto(TrainingSession ts);
}
