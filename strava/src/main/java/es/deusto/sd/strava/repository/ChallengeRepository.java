package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.Challenge;

import java.util.*;

public class ChallengeRepository {

    private static final Map<Long, Challenge> challengeStorage = new HashMap<>();
    private static long currentId = 1L;

    /**
     * Devuelve todos los retos en memoria.
     */
    public List<Challenge> findAll() {
        return new ArrayList<>(challengeStorage.values());
    }

    /**
     * Busca un Challenge por su ID.
     */
    public Optional<Challenge> findById(Long id) {
        return Optional.ofNullable(challengeStorage.get(id));
    }

    /**
     * Crea o actualiza un Challenge en el repositorio en memoria.
     * Si no tiene ID, se asigna automáticamente.
     */
    public Challenge save(Challenge challenge) {
        if (challenge.getIdChallenge() == null) {
            challenge.setIdChallenge(currentId++);
        }
        challengeStorage.put(challenge.getIdChallenge(), challenge);
        return challenge;
    }

    /**
     * Elimina un Challenge por su ID.
     */
    public void deleteById(Long id) {
        challengeStorage.remove(id);
    }

    /**
     * Busca los retos cuya fecha de fin sea posterior a la fecha dada.
     */
    public List<Challenge> findByEndDateAfter(Date date) {
        List<Challenge> result = new ArrayList<>();
        for (Challenge ch : challengeStorage.values()) {
            if (ch.getEndDate() != null && ch.getEndDate().after(date)) {
                result.add(ch);
            }
        }
        return result;
    }
}
