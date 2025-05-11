package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//PAGINACIÓN PARA RETOS ACTIVOS
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Page<Challenge> findByEndDateAfter(LocalDate date, Pageable pageable);
}
