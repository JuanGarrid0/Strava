package es.deusto.sd.strava.service;

import es.deusto.sd.strava.entity.Token;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.repository.TokenRepository;
import es.deusto.sd.strava.repository.UserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository userRepository,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    /**
     * Registers a new user by "validating" email with external service (dummy check here).
     */
    public User registerUser(String email,
                             String name,
                             boolean isValidFromExternalService) {
        if (!isValidFromExternalService) {
            throw new IllegalArgumentException("Email not validated by external provider.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("User with email " + email + " already exists.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        // (Opcional) set other fields
        return userRepository.save(newUser);
    }

    /**
     * Logs in by verifying credentials externally, then creating a new Token.
     */
    public Token loginUser(String email, String password, boolean isAuthFromExternal) {
        if (!isAuthFromExternal) {
            throw new IllegalArgumentException("Invalid credentials from external provider.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Token token = new Token();
        token.setValueToken(UUID.randomUUID().toString());
        token.setCreationDate(Date.from(Instant.now()));
        token.setUser(user);

        return tokenRepository.save(token);
    }

    /**
     * Invalidates a token by removing it from the in-memory storage.
     */
    public void logoutUser(String tokenValue) {
        // Nota: en tu repositorio en memoria, el método se llama findByValueToken(...)
        Optional<Token> existingToken = tokenRepository.findByValueToken(tokenValue);
        existingToken.ifPresent(token -> tokenRepository.deleteById(token.getIdToken()));
    }
}
