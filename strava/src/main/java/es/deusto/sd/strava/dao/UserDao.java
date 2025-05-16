package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserDao {
    private final UserRepository repo;

    public UserDao(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public User save(User user) {
        return repo.save(user);
    }
}
