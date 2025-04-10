package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.User;

import java.util.*;

public class UserRepository {

    private static final Map<Long, User> userStorage = new HashMap<>();
    private static long currentId = 1L;

    /**
     * Devuelve todos los usuarios en memoria.
     */
    public List<User> findAll() {
        return new ArrayList<>(userStorage.values());
    }

    /**
     * Busca un User por su ID.
     */
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    /**
     * Crea o actualiza un User en el repositorio en memoria.
     * Si el User no tiene ID, se asigna automáticamente.
     */
    public User save(User user) {
        if (user.getIdUser() == null) {
            user.setIdUser(currentId++);
        }
        userStorage.put(user.getIdUser(), user);
        return user;
    }

    /**
     * Elimina un User por su ID.
     */
    public void deleteById(Long id) {
        userStorage.remove(id);
    }

    /**
     * Busca un User opcional por su email.
     */
    public Optional<User> findByEmail(String email) {
        return userStorage.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * Verifica si existe un User con ese email.
     */
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
