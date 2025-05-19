package es.deusto.sd.strava.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;

@Repository
public class GoogleDao {
    private final RestTemplate restTemplate;
    private final String googleUrl;

    public GoogleDao(RestTemplate restTemplate,
                     @Value("${external.google.url:http://localhost:8081/google}") String googleUrl) {
        this.restTemplate = restTemplate;
        this.googleUrl = googleUrl;
    }

    /**
     * Checks if the given email is registered in Google service
     */
    public boolean validateEmail(String email) {
        String url = googleUrl + "/validate?email=" + email;
        ResponseEntity<Boolean> resp = restTemplate.getForEntity(url, Boolean.class);
        Boolean body = resp.getBody();
        return body != null && body;
    }

    
    /**
     * Si el usuario no existía, lo registra en Google automáticamente.
     */
    public void registerExternal(String email, String name, LocalDate birthDate) {
        String url = UriComponentsBuilder
            .fromHttpUrl(googleUrl + "/register")
            .queryParam("email", email)
            .queryParam("name", name)
            .queryParam("birthDate", birthDate.toString())
            .toUriString();
        restTemplate.postForEntity(url, null, Void.class);
    }
}