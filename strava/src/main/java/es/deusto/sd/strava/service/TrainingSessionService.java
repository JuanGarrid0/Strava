package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.TrainingSessionDao;
import es.deusto.sd.strava.dto.TrainingSessionCreateDTO;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.mapper.TrainingSessionMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingSessionService {

    private final TrainingSessionDao dao;
    private final TrainingSessionMapper mapper;

    public TrainingSessionService(TrainingSessionDao dao,
                                  TrainingSessionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Transactional
    public TrainingSessionDTO create(TrainingSessionCreateDTO dto, User user) {
        var entity = mapper.toEntity(dto);
        entity.setUser(user);
        var saved = dao.save(entity);
        return mapper.toDto(saved);
    }

    public List<TrainingSessionDTO> getLatest(User user, int limit) {
        return dao.findLatestByUser(user, PageRequest.of(0, limit))
                  .stream()
                  .map(mapper::toDto)
                  .collect(Collectors.toList());
    }

    public List<TrainingSessionDTO> getBetween(User user, LocalDateTime from, LocalDateTime to) {
        return dao.findByUserAndDateRange(user, from, to)
                  .stream()
                  .map(mapper::toDto)
                  .collect(Collectors.toList());
    }
}
