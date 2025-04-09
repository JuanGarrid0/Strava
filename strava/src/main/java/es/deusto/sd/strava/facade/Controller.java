package es.deusto.sd.strava.facade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    @PostMapping("/login")      // login
    public ResponseEntity<String> login(@RequestBody String name) {        
        return new ResponseEntity<>("HelloWorld!!!", HttpStatus.OK);
    }
    @PostMapping("/logout")      // logout
    public ResponseEntity<String> logout(@RequestBody String name) {        
        return new ResponseEntity<>("HelloWorld!!!", HttpStatus.OK);
    }
    @PostMapping("/register")    // post registro user
    public ResponseEntity<String> postRegister(@RequestBody String name) {
        return new ResponseEntity<>("Hello, " + name + "!", HttpStatus.OK);
    }
    @PostMapping("/createSesion")    // post sesion
    public ResponseEntity<String> postStravaSesion(@RequestBody String name) {
        return new ResponseEntity<>("Hello, " + name + "!", HttpStatus.OK);
    }
    @PostMapping("/createChallenge")    // post reto
    public ResponseEntity<String> postStravaChallenge(@RequestBody String name) {
        return new ResponseEntity<>("Hello, " + name + "!", HttpStatus.OK);
    }  
    @PostMapping("/acceptChallenge")    // post challenge accepted
    public ResponseEntity<String> postAcceptChallenge(@RequestBody String name) {
        return new ResponseEntity<>("Hello, " + name + "!", HttpStatus.OK);
    }
    @GetMapping("/sesion")      // get sesion
    public ResponseEntity<String> getSesions() {        
        return new ResponseEntity<>("HelloWorld!!!", HttpStatus.OK);
    }
    @GetMapping("/challenges")      // get challenge
    public ResponseEntity<String> getChallenges() {        
        return new ResponseEntity<>("HelloWorld!!!", HttpStatus.OK);
    }
    @GetMapping("/acceptedChallenges")      // get challenges accepted
    public ResponseEntity<String> getAcceptedChallenges() {        
        return new ResponseEntity<>("HelloWorld!!!", HttpStatus.OK);
    }

}