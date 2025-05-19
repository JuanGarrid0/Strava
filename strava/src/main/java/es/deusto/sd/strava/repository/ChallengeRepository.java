package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    /**
     * Retorna retos cuya fecha de fin es >= hoy, ordenados por fecha de inicio descendente.
     */
    Page<Challenge> findByEndDateAfterOrderByStartDateDesc(LocalDate today, Pageable pageable);
}