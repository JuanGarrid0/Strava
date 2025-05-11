package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ChallengeAcceptanceDao {
    private final ChallengeAcceptanceRepository repo;

    @Autowired
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