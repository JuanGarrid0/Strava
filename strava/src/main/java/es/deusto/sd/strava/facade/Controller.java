// File: src/main/java/es/deusto/sd/strava/facade/Controller.java
package es.deusto.sd.strava.facade;

import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {

    @RestController
    @RequestMapping("/users")
    public static class UserController {
        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping("/register")
        public ResponseEntity<UserProfileDTO> register(
                @Valid @RequestBody UserRegisterDTO dto) {
            UserProfileDTO profile = userService.register(dto);
            return ResponseEntity.ok(profile);
        }

        @PostMapping("/login")
        public ResponseEntity<TokenDTO> login(
                @Valid @RequestBody UserLoginDTO dto) {
            TokenDTO token = userService.login(dto);
            return ResponseEntity.ok(token);
        }

        @PostMapping("/logout")
        public ResponseEntity<Void> logout(
                @RequestHeader("Authorization") String tokenValue) {
            userService.logout(tokenValue);
            return ResponseEntity.noContent().build();
        }
    }

    @RestController
    @RequestMapping("/sessions")
    public static class TrainingSessionController {
        private final TrainingSessionService sessionService;
        private final UserService userService;

        public TrainingSessionController(TrainingSessionService sessionService,
                                         UserService userService) {
            this.sessionService = sessionService;
            this.userService = userService;
        }

        @PostMapping
        public ResponseEntity<TrainingSessionDTO> create(
                @RequestHeader("Authorization") String token,
                @Valid @RequestBody TrainingSessionCreateDTO dto) {
            User user = userService.getUserFromToken(token);
            TrainingSessionDTO created = sessionService.create(dto, user);
            return ResponseEntity.ok(created);
        }

        @GetMapping("/latest")
        public ResponseEntity<List<TrainingSessionDTO>> getLatest(
                @RequestHeader("Authorization") String token,
                @RequestParam(defaultValue = "5") int limit) {
            User user = userService.getUserFromToken(token);
            List<TrainingSessionDTO> list = sessionService.getLatest(user, limit);
            return ResponseEntity.ok(list);
        }

        @GetMapping
        public ResponseEntity<List<TrainingSessionDTO>> getBetween(
                @RequestHeader("Authorization") String token,
                @Valid DateRangeDTO range) {
            User user = userService.getUserFromToken(token);
            List<TrainingSessionDTO> list = sessionService.getBetween(
                    user,
                    range.getFrom().atStartOfDay(),
                    range.getTo().atTime(23, 59)
            );
            return ResponseEntity.ok(list);
        }
    }

    @RestController
    @RequestMapping("/challenges")
    public static class ChallengeController {
        private final ChallengeService challengeService;
        private final UserService userService;

        public ChallengeController(ChallengeService challengeService,
                                   UserService userService) {
            this.challengeService = challengeService;
            this.userService = userService;
        }

        @PostMapping
        public ResponseEntity<ChallengeDTO> create(
                @RequestHeader("Authorization") String token,
                @Valid @RequestBody ChallengeCreateDTO dto) {
            userService.getUserFromToken(token);
            ChallengeDTO created = challengeService.create(dto);
            return ResponseEntity.ok(created);
        }

        @GetMapping("/active")
        public ResponseEntity<List<ChallengeDTO>> getActive(
                @RequestHeader("Authorization") String token,
                @RequestParam(defaultValue = "5") int limit) {
            userService.getUserFromToken(token);
            List<ChallengeDTO> list = challengeService.getActive(limit);
            return ResponseEntity.ok(list);
        }

        @PostMapping("/{id}/join")
        public ResponseEntity<Void> join(
                @RequestHeader("Authorization") String token,
                @PathVariable Long id) {
            User user = userService.getUserFromToken(token);
            challengeService.join(user, id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/accepted")
        public ResponseEntity<List<UserChallengeDTO>> getUserChallenges(
                @RequestHeader("Authorization") String token) {
            User user = userService.getUserFromToken(token);
            List<UserChallengeDTO> list = challengeService.getUserChallenges(user);
            return ResponseEntity.ok(list);
        }
    }
}
