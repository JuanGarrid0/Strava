package es.deusto.sd.strava.repository;

import es.deusto.sd.strava.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


//CONSULTA-ELIMINACIÓN POR VALOR DEL TOKEN Y OBTENCIÓN POR USUARIO
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String value);
    void deleteByValue(String value);
    List<Token> findByUser(User user);
}
