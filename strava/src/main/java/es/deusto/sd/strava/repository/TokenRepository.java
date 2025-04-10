package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.Token;

import java.util.*;

public class TokenRepository {

    private static final Map<Long, Token> tokenStorage = new HashMap<>();
    private static long currentId = 1L;

    /**
     * Devuelve todos los tokens en memoria.
     */
    public List<Token> findAll() {
        return new ArrayList<>(tokenStorage.values());
    }

    /**
     * Busca un Token por su ID.
     */
    public Optional<Token> findById(Long id) {
        return Optional.ofNullable(tokenStorage.get(id));
    }

    /**
     * Crea o actualiza un Token en el repositorio en memoria.
     * Si no tiene ID, se asigna automáticamente.
     */
    public Token save(Token token) {
        if (token.getIdToken() == 0) {
            token.setIdToken(currentId++);
        }
        tokenStorage.put(token.getIdToken(), token);
        return token;
    }

    /**
     * Elimina un Token por su ID.
     */
    public void deleteById(Long id) {
        tokenStorage.remove(id);
    }

    /**
     * Busca un Token por el valor de su token (valueToken).
     */
    public Optional<Token> findByValueToken(String valueToken) {
        return tokenStorage.values().stream()
                .filter(t -> t.getValueToken().equals(valueToken))
                .findFirst();
    }
}
