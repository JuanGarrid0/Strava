package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;

import java.util.*;

public class TrainingSessionRepository {

    private static final Map<Long, TrainingSession> sessionStorage = new HashMap<>();
    private static long currentId = 1L;

    /**
     * Devuelve todas las TrainingSessions en memoria.
     */
    public List<TrainingSession> findAll() {
        return new ArrayList<>(sessionStorage.values());
    }

    /**
     * Busca una TrainingSession por su ID.
     */
    public Optional<TrainingSession> findById(Long id) {
        return Optional.ofNullable(sessionStorage.get(id));
    }

    /**
     * Crea o actualiza una TrainingSession en el repositorio en memoria.
     * Si no tiene ID, se asigna automáticamente.
     */
    public TrainingSession save(TrainingSession session) {
        if (session.getIdTrainingSesion() == 0) {
            session.setIdTrainingSesion(currentId++);
        }
        sessionStorage.put(session.getIdTrainingSesion(), session);
        return session;
    }

    /**
     * Elimina una TrainingSession por su ID.
     */
    public void deleteById(Long id) {
        sessionStorage.remove(id);
    }

    /**
     * Obtiene las TrainingSessions de un usuario, ordenadas por fecha de inicio descendente.
     */
    public List<TrainingSession> findByUserOrderByStartDateDesc(User user) {
        List<TrainingSession> userSessions = new ArrayList<>();
        for (TrainingSession ts : sessionStorage.values()) {
            if (ts.getUser() == user) {
                userSessions.add(ts);
            }
        }
        // Ordenar por startDate descendente
        userSessions.sort((s1, s2) -> {
            if (s1.getStartDate() == null && s2.getStartDate() == null) return 0;
            if (s1.getStartDate() == null) return 1;
            if (s2.getStartDate() == null) return -1;
            return s2.getStartDate().compareTo(s1.getStartDate());
        });
        return userSessions;
    }

    /**
     * Obtiene las TrainingSessions de un usuario entre dos fechas.
     */
    public List<TrainingSession> findByUserAndStartDateBetween(User user, Date startDate, Date endDate) {
        List<TrainingSession> result = new ArrayList<>();
        for (TrainingSession ts : sessionStorage.values()) {
            if (ts.getUser() == user && ts.getStartDate() != null) {
                Date sessionDate = ts.getStartDate();
                if (!sessionDate.before(startDate) && !sessionDate.after(endDate)) {
                    result.add(ts);
                }
            }
        }
        return result;
    }
}
