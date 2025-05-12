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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StravaMetaApplicationTests {
    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    public void testStravaRegisterLoginWithMeta() {
        // 1. Register user in Strava via META provider
        UserRegisterDTO registerDto = new UserRegisterDTO();
        registerDto.setEmail("meta_strava@example.com");
        registerDto.setName("Strava Meta");
        registerDto.setBirthDate(LocalDate.of(1995,5,5));
        registerDto.setProvider(AuthProvider.META);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRegisterDTO> regEntity = new HttpEntity<>(registerDto, headers);

        ResponseEntity<String> regResponse = restTemplate.postForEntity(
            url("/users/register"), regEntity, String.class);
        assertThat(regResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 2. Login via META provider
        UserLoginDTO loginDto = new UserLoginDTO();
        loginDto.setEmail("meta_strava@example.com");
        loginDto.setPassword("ignored");
        loginDto.setProvider(AuthProvider.META);
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
        ResponseEntity<List> resp = restTemplate.exchange(
            url("/sessions/latest?limit=1"), HttpMethod.GET, authEntity, List.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
