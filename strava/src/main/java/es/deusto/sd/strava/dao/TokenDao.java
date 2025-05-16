package es.deusto.sd.strava.dao;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.repository.*;
import java.util.List;
import java.util.Optional;

public class TokenDao {
    private final TokenRepository repo;

    public TokenDao(TokenRepository repo) {
        this.repo = repo;
    }

    public Optional<Token> findByValue(String value) {
        return repo.findByValue(value);
    }

    public void deleteByValue(String value) {
        repo.deleteByValue(value);
    }

    public List<Token> findByUser(User user) {
        return repo.findByUser(user);
    }

    public Token save(Token token) {
        return repo.save(token);
    }
}
