package es.deusto.sd.strava.repository;
import es.deusto.sd.strava.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


//BUSQUEDA Y EXISTENCIA POR EMAIL
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
