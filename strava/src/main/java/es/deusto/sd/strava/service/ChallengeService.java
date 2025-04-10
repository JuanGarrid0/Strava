package es.deusto.sd.strava.service;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.ChallengeAcceptanceRepository;
import es.deusto.sd.strava.repository.ChallengeRepository;
import es.deusto.sd.strava.repository.TrainingSessionRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeAcceptanceRepository acceptanceRepository;
    private final TrainingSessionRepository trainingSessionRepository;

    public ChallengeService(ChallengeRepository challengeRepository,
                            ChallengeAcceptanceRepository acceptanceRepository,
                            TrainingSessionRepository trainingSessionRepository) {
        this.challengeRepository = challengeRepository;
        this.acceptanceRepository = acceptanceRepository;
        this.trainingSessionRepository = trainingSessionRepository;
    }

    /**
     * Creates a new challenge (no 'creator' field in your entity yet).
     */
    public Challenge createChallenge(String name,
                                     Date startDate,
                                     Date endDate,
                                     double objectiveValue,
                                     Sport sport) {
        Challenge challenge = new Challenge();
        challenge.setName(name);
        challenge.setStartDate(startDate);
        challenge.setEndDate(endDate);
        challenge.setObjectiveValue(objectiveValue);
        challenge.setSport(sport);

        return challengeRepository.save(challenge);
    }

    /**
     * Returns all active challenges (whose endDate is after 'today').
     */
    public List<Challenge> listActiveChallenges() {
        LocalDate today = LocalDate.now();
        // Convert LocalDate to java.sql.Date
        Date todayAsDate = java.sql.Date.valueOf(today);
        return challengeRepository.findByEndDateAfter(todayAsDate);
    }

    /**
     * User joins an existing challenge -> create a ChallengeAcceptance record.
     */
    public ChallengeAcceptance joinChallenge(User user, Challenge challenge) {
        // Check if the user already accepted this challenge
        if (acceptanceRepository.existsByUserAndChallenge(user, challenge)) {
            throw new IllegalStateException("User already joined this challenge.");
        }

        ChallengeAcceptance acceptance = new ChallengeAcceptance();
        acceptance.setUser(user);
        acceptance.setChallenge(challenge);
        acceptance.setAcceptanceDate(new Date());

        return acceptanceRepository.save(acceptance);
    }

    /**
     * Retrieves challenges that a user has already accepted.
     */
    public List<Challenge> getAcceptedChallenges(User user) {
        List<ChallengeAcceptance> acceptances = acceptanceRepository.findByUser(user);
        // Dado que en tu ChallengeAcceptance actual tienes un `private Challenge challenge;`
        // no es una lista, hacemos un mapeo directo:
        return acceptances.stream()
                .map(ChallengeAcceptance::getChallenge)
                .collect(Collectors.toList());
    }

    /**
     * Calculates user progress in a challenge based on training sessions.
     */
    public double calculateChallengeProgress(User user, Challenge challenge) {
        // 1. Retrieve all user sessions that match the challenge's dates
        Date start = challenge.getStartDate();
        Date end = challenge.getEndDate();
        Sport challengeSport = challenge.getSport();

        List<TrainingSession> sessions = trainingSessionRepository.findByUserAndStartDateBetween(user, start, end);

        // 2. Filter by same sport
        sessions = sessions.stream()
                .filter(s -> s.getSport() == challengeSport)
                .collect(Collectors.toList());

        // 3. Sum up distance or time
        double sum;
        if (isDistanceChallenge(challenge)) {
            sum = sessions.stream().mapToDouble(TrainingSession::getDistance).sum();
        } else {
            sum = sessions.stream().mapToDouble(TrainingSession::getDuration).sum();
        }

        // 4. Compute ratio
        double objective = challenge.getObjectiveValue(); // e.g. 50.0 km or 240.0 min
        if (objective <= 0) {
            return 0;
        }
        double progress = (sum / objective) * 100.0;

        // 5. Cap at 100% if you wish
        return Math.min(progress, 100.0);
    }

    private boolean isDistanceChallenge(Challenge challenge) {
        // Your logic to decide if this challenge is by distance or by time
        // For instance:
        return challenge.getObjectiveValue() >= 50;
    }
}
