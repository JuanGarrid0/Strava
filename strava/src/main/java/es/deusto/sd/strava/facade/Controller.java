package es.deusto.sd.strava.facade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {


@PostMapping("/strava")    // Server test
public ResponseEntity<String> postStrava(@RequestBody String name) {
    return new ResponseEntity<>("Hello, " + name + "!", HttpStatus.OK);
}
}