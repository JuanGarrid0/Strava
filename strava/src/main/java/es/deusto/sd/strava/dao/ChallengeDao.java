package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.repository.ChallengeAcceptanceRepository;
import es.deusto.sd.strava.repository.ChallengeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ChallengeDao {

    private final ChallengeRepository challengeRepo;
    private final ChallengeAcceptanceRepository acceptanceRepo;

    public ChallengeDao(ChallengeRepository challengeRepo,
                        ChallengeAcceptanceRepository acceptanceRepo) {
        this.challengeRepo = challengeRepo;
        this.acceptanceRepo = acceptanceRepo;
    }

    public Challenge save(Challenge challenge) {
        return challengeRepo.save(challenge);
    }

    public Page<Challenge> findActive(LocalDate today, Pageable pageable) {
        return challengeRepo.findByEndDateAfterOrderByStartDateDesc(today, pageable);
    }

    public Challenge findById(Long id) {
        return challengeRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Challenge not found: " + id));
    }

    public ChallengeAcceptance saveAcceptance(ChallengeAcceptance acc) {
        return acceptanceRepo.save(acc);
    }

    public List<ChallengeAcceptance> findByUser(User user) {
        return acceptanceRepo.findByUser(user);
    }
}