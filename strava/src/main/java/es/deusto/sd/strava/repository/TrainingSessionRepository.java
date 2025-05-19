package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

//PAGINACIÓN PARA ÚLTIMAS SESIONES Y FILTRADO POR RANGO DE FECHAS
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    /**
     * Recupera páginas de sesiones ordenadas por fecha de inicio descendente.
     */
    Page<TrainingSession> findByUserOrderByStartDesc(User user, Pageable pageable);

    /**
     * Recupera todas las sesiones de usuario entre dos fechas, orden descendente.
     */
    List<TrainingSession> findByUserAndStartBetweenOrderByStartDesc(
        User user, LocalDateTime start, LocalDateTime end);
}