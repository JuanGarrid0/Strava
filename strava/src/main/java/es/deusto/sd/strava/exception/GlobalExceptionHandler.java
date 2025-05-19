package es.deusto.sd.strava.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAny(Exception ex) {
        ex.printStackTrace();  // ❶ Imprime stack en consola
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getClass().getSimpleName()
                    + " – " + ex.getMessage());  // ❷ Devuelve mensaje al cliente
    }
}
