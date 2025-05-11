package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//LISTADO DE ACEPTACIONES POR USUARIO Y COMPROBACIÓN DE DUPLICADOS
public interface ChallengeAcceptanceRepository extends JpaRepository<ChallengeAcceptance, Long> {
    List<ChallengeAcceptance> findByUser(User user);
    boolean existsByUserAndChallenge(User user, Challenge challenge);
}
