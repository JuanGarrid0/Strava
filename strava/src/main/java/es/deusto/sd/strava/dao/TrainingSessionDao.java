package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingSessionDao {
    private final TrainingSessionRepository repo;

    @Autowired
    public TrainingSessionDao(TrainingSessionRepository repo) {
        this.repo = repo;
    }

    public Page<TrainingSession> findLatestByUser(User user, Pageable pageable) {
        return repo.findByUserOrderByStartDesc(user, pageable);
    }

    public List<TrainingSession> findByUserAndDateRange(User user, LocalDateTime start, LocalDateTime end) {
        return repo.findByUserAndStartBetween(user, start, end);
    }

    public TrainingSession save(TrainingSession session) {
        return repo.save(session);
    }
}
