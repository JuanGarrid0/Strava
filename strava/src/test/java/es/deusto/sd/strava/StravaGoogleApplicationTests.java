package es.deusto.sd.strava;

import es.deusto.sd.strava.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StravaGoogleApplicationTests {
    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void setup() {
        restTemplate = new RestTemplate();
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    public void testGoogleRegistrationLoginFlow() {
        // Ensure Google service is running at 8081
        // 1. Register user in Strava via Google provider
        UserRegisterDTO registerDto = new UserRegisterDTO();
        registerDto.setEmail("user@example.com");
        registerDto.setName("Test User");
        registerDto.setBirthDate(LocalDate.of(1990,1,1));
        registerDto.setProvider(AuthProvider.GOOGLE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRegisterDTO> regEntity = new HttpEntity<>(registerDto, headers);
        ResponseEntity<String> regResponse = restTemplate.postForEntity(
                url("/users/register"), regEntity, String.class);
        assertThat(regResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 2. Login via Google provider
        UserLoginDTO loginDto = new UserLoginDTO();
        loginDto.setEmail("user@example.com");
        loginDto.setPassword("ignored");
        loginDto.setProvider(AuthProvider.GOOGLE);
        HttpEntity<UserLoginDTO> loginEntity = new HttpEntity<>(loginDto, headers);

        ResponseEntity<TokenDTO> loginResponse = restTemplate.postForEntity(
                url("/users/login"), loginEntity, TokenDTO.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        TokenDTO token = loginResponse.getBody();
        assertThat(token).isNotNull();
        assertThat(token.getValue()).isNotBlank();

        // 3. Access protected endpoint with token
        headers.set("Authorization", token.getValue());
        HttpEntity<Void> authEntity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = restTemplate.exchange(
                url("/sessions/latest?limit=1"), HttpMethod.GET, authEntity, String.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}