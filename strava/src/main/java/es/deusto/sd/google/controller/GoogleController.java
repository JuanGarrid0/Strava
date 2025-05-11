package es.deusto.sd.google.controller;

import es.deusto.sd.google.entity.*;
import es.deusto.sd.google.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/google")
public class GoogleController {
    private final UserGoogleRepository repo;

    @Autowired
    public GoogleController(UserGoogleRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateEmail(@RequestParam String email) {
        boolean exists = repo.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/register")
    public ResponseEntity<UserGoogle> register(@RequestBody UserGoogle user) {
        if (repo.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        UserGoogle saved = repo.save(user);
        return ResponseEntity.ok(saved);
    }
}
