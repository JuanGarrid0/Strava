package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//PAGINACIÓN PARA ÚLTIMAS SESIONES Y FILTRADO POR RANGO DE FECHAS
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    Page<TrainingSession> findByUserOrderByStartDesc(User user, Pageable pageable);
    List<TrainingSession> findByUserAndStartBetween(User user,
                                                   LocalDateTime start,
                                                   LocalDateTime end);
}
