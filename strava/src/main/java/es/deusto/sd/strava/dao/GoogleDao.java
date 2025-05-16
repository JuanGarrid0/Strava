package es.deusto.sd.strava.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
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
}