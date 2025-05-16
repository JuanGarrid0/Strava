package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ChallengeDao {
    private final ChallengeRepository repo;

    public ChallengeDao(ChallengeRepository repo) {
        this.repo = repo;
    }

    public Page<Challenge> findActive(LocalDate today, Pageable pageable) {
        return repo.findByEndDateAfter(today, pageable);
    }

    public Challenge save(Challenge challenge) {
        return repo.save(challenge);
    }

    public Optional<Challenge> findById(Long id) {
        return repo.findById(id);
    }
}
