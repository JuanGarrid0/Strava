package es.deusto.sd.strava.service;

import es.deusto.sd.strava.entity.Sport;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.repository.TrainingSessionRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    /**
     * Creates a new TrainingSession for the given user.
     */
    public TrainingSession createTrainingSession(User user,
                                                 String title,
                                                 Sport sport,
                                                 double distance,
                                                 Date startDate,
                                                 LocalDateTime startTime,
                                                 int duration) {
        TrainingSession session = new TrainingSession();
        session.setUser(user);
        session.setTitle(title);
        session.setSport(sport);
        session.setDistance(distance);
        session.setStartDate(startDate);
        session.setStartTime(startTime);
        session.setDuration(duration);

        return trainingSessionRepository.save(session);
    }

    /**
     * Returns the latest N sessions for a user, ordered by date/time descending.
     */
    public List<TrainingSession> getLatestSessions(User user, int limit) {
        // Llamamos al método para orden descendente:
        List<TrainingSession> sessions = trainingSessionRepository.findByUserOrderByStartDateDesc(user);
        return sessions.stream().limit(limit).toList();
    }

    /**
     * Returns all sessions for a user between two dates.
     */
    public List<TrainingSession> getSessionsBetweenDates(User user, Date startDate, Date endDate) {
        return trainingSessionRepository.findByUserAndStartDateBetween(user, startDate, endDate);
    }
}
