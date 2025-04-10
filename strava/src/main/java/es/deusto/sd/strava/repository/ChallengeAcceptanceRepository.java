package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import es.deusto.sd.strava.entity.User;

import java.util.*;

public class ChallengeAcceptanceRepository {

    private static final Map<Long, ChallengeAcceptance> acceptanceStorage = new HashMap<>();
    private static long currentId = 1L;

    /**
     * Devuelve todas las ChallengeAcceptances en memoria.
     */
    public List<ChallengeAcceptance> findAll() {
        return new ArrayList<>(acceptanceStorage.values());
    }

    /**
     * Busca un ChallengeAcceptance por su ID.
     */
    public Optional<ChallengeAcceptance> findById(Long id) {
        return Optional.ofNullable(acceptanceStorage.get(id));
    }

    /**
     * Crea o actualiza una ChallengeAcceptance en el repositorio en memoria.
     * Si no tiene ID, se asigna automáticamente.
     */
    public ChallengeAcceptance save(ChallengeAcceptance acceptance) {
        if (acceptance.getIdChallengeAcceptance() == null) {
            acceptance.setIdChallengeAcceptance(currentId++);
        }
        acceptanceStorage.put(acceptance.getIdChallengeAcceptance(), acceptance);
        return acceptance;
    }

    /**
     * Elimina una ChallengeAcceptance por su ID.
     */
    public void deleteById(Long id) {
        acceptanceStorage.remove(id);
    }

    /**
     * Verifica si un usuario ya aceptó un reto concreto.
     */
    public boolean existsByUserAndChallenge(User user, Challenge challenge) {
        return acceptanceStorage.values().stream()
                .anyMatch(ac -> ac.getUser() == user && ac.getChallenge() == challenge);
    }

    /**
     * Encuentra las ChallengeAcceptances de un usuario.
     */
    public List<ChallengeAcceptance> findByUser(User user) {
        List<ChallengeAcceptance> result = new ArrayList<>();
        for (ChallengeAcceptance ac : acceptanceStorage.values()) {
            if (ac.getUser() == user) {
                result.add(ac);
            }
        }
        return result;
    }
}
