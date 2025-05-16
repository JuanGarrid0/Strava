package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ChallengeAcceptanceDao {
    private final ChallengeAcceptanceRepository repo;

    public ChallengeAcceptanceDao(ChallengeAcceptanceRepository repo) {
        this.repo = repo;
    }

    public List<ChallengeAcceptance> findByUser(User user) {
        return repo.findByUser(user);
    }

    public boolean existsByUserAndChallenge(User user, Challenge challenge) {
        return repo.existsByUserAndChallenge(user, challenge);
    }

    public ChallengeAcceptance save(ChallengeAcceptance acceptance) {
        return repo.save(acceptance);
    }
}