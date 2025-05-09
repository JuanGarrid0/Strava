package es.deusto.sd.strava.facade;

import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.TrainingSessionService;
import es.deusto.sd.strava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.deusto.sd.strava.dto.*;

import java.util.Date;
import java.util.List;

/**
 * Ejemplo de controlador REST sin usar JPA ni transacciones.
 * Depende de servicios con repositorios en memoria.
 */
@RestController
@RequestMapping("/api") // Opcional: prefijo general para tus endpoints
public class Controller {

    private final UserService userService;
    private final TrainingSessionService trainingSessionService;
    private final ChallengeService challengeService;

    // Inyecta (o crea) los servicios. 
    // Si no usas configuración de Spring Boot, podrías instanciarlos manualmente aquí.
    public Controller(UserService userService,
                      TrainingSessionService trainingSessionService,
                      ChallengeService challengeService) {
        this.userService = userService;
        this.trainingSessionService = trainingSessionService;
        this.challengeService = challengeService;
    }

    // --- EJEMPLOS DE ENDPOINTS ---

    // ----------------------------------
    //           LOGIN / LOGOUT
    // ----------------------------------

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Token token = userService.loginUser(
                    request.getEmail(),
                    request.getPassword(),
                    request.isAuthFromExternal());
            return ResponseEntity.ok("Logged in! Token: " + token.getValueToken());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String tokenValue) {
        try {
            userService.logoutUser(tokenValue);
            return ResponseEntity.ok("Logged out successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //           REGISTRO DE USER
    // ----------------------------------

    @PostMapping("/register")
    public ResponseEntity<?> postRegister(@RequestBody RegisterRequest request) {
        try {
            User user = userService.registerUser(
                    request.getEmail(),
                    request.getName(),
                    request.isValidFromExternalService());
            return ResponseEntity.ok("User created with ID: " + user.getIdUser());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //       CREACIÓN DE SESIONES
    // ----------------------------------

    @PostMapping("/createSession")
    public ResponseEntity<?> postStravaSession(@RequestBody CreationTrainingSessionRequest sessionReq) {
        // Ejemplo: sessionReq podría contener userId, title, sport, distance, etc.
        try {
            // Buscar user en tu repositorio (a través del servicio) si no lo recibes como objeto
            // Supongamos que la request te da un userId
            User user = null; // Por ejemplo: userService.findUserById(sessionReq.getUserId())
                              // (Necesitarías un método findById en userService)

            // Hardcode de ejemplo, deberás adaptarlo
            user = new User();
            user.setIdUser(1L);
            user.setEmail("fake@email");
            user.setName("FakeName");

            TrainingSession newSession = trainingSessionService.createTrainingSession(
                    user,
                    sessionReq.getTitle(),
                    sessionReq.getSport(),
                    sessionReq.getDistance(),
                    sessionReq.getStartDate(),
                    sessionReq.getStartTime(),
                    sessionReq.getDuration()
            );
            return ResponseEntity.ok("Session created with ID: " + newSession.getIdTrainingSesion());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //       CREACIÓN DE CHALLENGE
    // ----------------------------------

    @PostMapping("/createChallenge")
    public ResponseEntity<?> postStravaChallenge(@RequestBody CreationChallengeRequest challengeReq) {
        try {
            Challenge challenge = challengeService.createChallenge(
                    challengeReq.getName(),
                    challengeReq.getStartDate(),
                    challengeReq.getEndDate(),
                    challengeReq.getObjectiveValue(),
                    challengeReq.getSport()
            );
            return ResponseEntity.ok("Challenge created with ID: " + challenge.getIdChallenge());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //     ACEPTAR UN CHALLENGE
    // ----------------------------------

    @PostMapping("/acceptChallenge")
    public ResponseEntity<?> postAcceptChallenge(@RequestBody AcceptChallengeRequest acceptReq) {
        try {
            // Igual que antes, necesitas buscar el User y Challenge por ID
            // con userService / challengeService. 
            // Aquí para simplificar, supongamos que tenemos user y challenge "ya" disponibles.
            User user = new User();
            user.setIdUser(acceptReq.getUserId());

            Challenge challenge = new Challenge();
            challenge.setIdChallenge(acceptReq.getChallengeId());

            ChallengeAcceptance acceptance = challengeService.joinChallenge(user, challenge);
            return ResponseEntity.ok("Challenge accepted with ID: " + acceptance.getIdChallengeAcceptance());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //     OBTENER SESIONES
    // ----------------------------------

    @GetMapping("/sesion")
    public ResponseEntity<?> getSesions(@RequestParam Long userId) {
        try {
            // Ejemplo: traemos todas o las últimas N
            // Un user real se buscaría: userService.findById(userId)
            User user = new User();
            user.setIdUser(userId);

            List<TrainingSession> sessions = trainingSessionService.getLatestSessions(user, 10);
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //     OBTENER CHALLENGES
    // ----------------------------------

    @GetMapping("/challenges")
    public ResponseEntity<?> getChallenges() {
        try {
            // Llamamos a challengeService.listActiveChallenges()
            List<Challenge> activeChallenges = challengeService.listActiveChallenges();
            return ResponseEntity.ok(activeChallenges);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ----------------------------------
    //  OBTENER CHALLENGES ACEPTADOS
    // ----------------------------------

    @GetMapping("/acceptedChallenges")
    public ResponseEntity<?> getAcceptedChallenges(@RequestParam Long userId) {
        try {
            // Buscar user real o instanciarlo
            User user = new User();
            user.setIdUser(userId);

            List<Challenge> accepted = challengeService.getAcceptedChallenges(user);
            return ResponseEntity.ok(accepted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
