package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import es.deusto.sd.strava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChallengeAcceptanceRepository extends JpaRepository<ChallengeAcceptance, Long> {
    List<ChallengeAcceptance> findByUser(User user);
    boolean existsByUserAndChallenge(User user, Challenge challenge);
}