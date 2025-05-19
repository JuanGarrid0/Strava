package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.repository.ChallengeAcceptanceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChallengeAcceptanceDao {
    private final ChallengeAcceptanceRepository repo;

    public ChallengeAcceptanceDao(ChallengeAcceptanceRepository repo) {
        this.repo = repo;
    }

    /**
     * Recupera todas las aceptaciones de retos de un usuario.
     */
    public List<ChallengeAcceptance> findByUser(User user) {
        return repo.findByUser(user);
    }

    /**
     * Comprueba si un usuario ya se ha apuntado a un reto.
     */
    public boolean existsByUserAndChallenge(User user, Challenge challenge) {
        return repo.existsByUserAndChallenge(user, challenge);
    }

    /**
     * Guarda o actualiza una aceptación de reto.
     */
    public ChallengeAcceptance save(ChallengeAcceptance acceptance) {
        return repo.save(acceptance);
    }
}