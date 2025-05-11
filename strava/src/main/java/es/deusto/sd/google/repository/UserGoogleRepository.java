package es.deusto.sd.google.repository;

import es.deusto.sd.google.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserGoogleRepository extends JpaRepository<UserGoogle, Long> {
    Optional<UserGoogle> findByEmail(String email);
    boolean existsByEmail(String email);
}