package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.repository.TrainingSessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TrainingSessionDao {

    private final TrainingSessionRepository repo;

    public TrainingSessionDao(TrainingSessionRepository repo) {
        this.repo = repo;
    }

    /**
     * Recupera una página de sesiones ordenadas por fecha de inicio descendente.
     */
    public Page<TrainingSession> findLatestByUser(User user, Pageable pageable) {
        return repo.findByUserOrderByStartDesc(user, pageable);
    }

    /**
     * Recupera todas las sesiones entre dos instantes, orden descendente.
     */
    public List<TrainingSession> findByUserAndDateRange(User user, LocalDateTime start, LocalDateTime end) {
        return repo.findByUserAndStartBetweenOrderByStartDesc(user, start, end);
    }

    /**
     * Guarda o actualiza una sesión.
     */
    public TrainingSession save(TrainingSession session) {
        return repo.save(session);
    }
}
