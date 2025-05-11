package es.deusto.sd.strava.service;

import es.deusto.sd.strava.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    private final Map<String, TokenInfo> tokenStore = new ConcurrentHashMap<>();
    private final long ttlMillis;
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public TokenService(@Value("${app.token.ttl:3600000}") long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public String generateToken(User user) {
        String token = String.valueOf(Instant.now().toEpochMilli());
        tokenStore.put(token, new TokenInfo(user, System.currentTimeMillis()));
        return token;
    }

    public Optional<User> getUser(String token) {
        TokenInfo info = tokenStore.get(token);
        if (info == null) {
            return Optional.empty();
        }
        if (System.currentTimeMillis() - info.timestamp > ttlMillis) {
            tokenStore.remove(token);
            return Optional.empty();
        }
        return Optional.of(info.user);
    }

    public void revokeToken(String token) {
        tokenStore.remove(token);
    }

    @PostConstruct
    private void startCleanup() {
        cleaner.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            tokenStore.entrySet().removeIf(e -> now - e.getValue().timestamp > ttlMillis);
        }, ttlMillis, ttlMillis, TimeUnit.MILLISECONDS);
    }

    private static class TokenInfo {
        final User user;
        final long timestamp;
        TokenInfo(User user, long timestamp) {
            this.user = user;
            this.timestamp = timestamp;
        }
    }
}
