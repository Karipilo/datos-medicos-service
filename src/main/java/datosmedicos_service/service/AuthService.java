package datosmedicos_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public HttpHeaders buildHeaders(String token) {

        HttpHeaders headers = new HttpHeaders();

        if (token != null && !token.isBlank()) {
            headers.setBearerAuth(token);
        }

        return headers;
    }

    public HttpEntity<Void> buildEntity(String token) {

        return new HttpEntity<>(buildHeaders(token));
    }

    public ResponseEntity<String> get(
            String url,
            String token
    ) {

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                buildEntity(token),
                String.class
        );
    }
}