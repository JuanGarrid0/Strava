package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//LISTADO DE ACEPTACIONES POR USUARIO Y COMPROBACIÓN DE DUPLICADOS
public interface ChallengeAcceptanceRepository extends JpaRepository<ChallengeAcceptance, Long> {
    List<ChallengeAcceptance> findByUser(User user);
    boolean existsByUserAndChallenge(User user, Challenge challenge);
}
