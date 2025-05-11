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

public class TokenDao {
    private final TokenRepository repo;

    @Autowired
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
