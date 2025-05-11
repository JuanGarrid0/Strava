package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.*;
import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingSessionService {
    private final TrainingSessionDao sessionDao;
    private final TrainingSessionMapper sessionMapper;

    @Autowired
    public TrainingSessionService(TrainingSessionDao sessionDao, TrainingSessionMapper sessionMapper) {
        this.sessionDao = sessionDao;
        this.sessionMapper = sessionMapper;
    }

    public TrainingSessionDTO create(TrainingSessionCreateDTO dto, User user) {
        TrainingSession session = sessionMapper.toEntity(dto);
        session.setUser(user);
        TrainingSession saved = sessionDao.save(session);
        return sessionMapper.toDto(saved);
    }

    public List<TrainingSessionDTO> getLatest(User user, int limit) {
        return sessionDao.findLatestByUser(user, PageRequest.of(0, limit))
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TrainingSessionDTO> getBetween(User user, LocalDateTime from, LocalDateTime to) {
        return sessionDao.findByUserAndDateRange(user, from, to)
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}