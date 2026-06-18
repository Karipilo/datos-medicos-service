package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buildHeaders_conToken_agregarBearerAuth() {
        HttpHeaders headers = authService.buildHeaders("mi-token");
        assertNotNull(headers);
        assertTrue(headers.containsKey("Authorization"));
    }

    @Test
    void buildHeaders_tokenVacio_noAgregarAuthorization() {
        HttpHeaders headers = authService.buildHeaders("");
        assertNotNull(headers);
        assertFalse(headers.containsKey("Authorization"));
    }

    @Test
    void buildHeaders_tokenNull_noAgregarAuthorization() {
        HttpHeaders headers = authService.buildHeaders(null);
        assertNotNull(headers);
        assertFalse(headers.containsKey("Authorization"));
    }

    @Test
    void buildEntity_conToken_retornaEntidad() {
        HttpEntity<Void> entity = authService.buildEntity("token123");
        assertNotNull(entity);
        assertNotNull(entity.getHeaders());
    }

    @Test
    void buildEntity_tokenVacio_retornaEntidad() {
        HttpEntity<Void> entity = authService.buildEntity("");
        assertNotNull(entity);
    }

    @Test
    void get_retornaRespuestaOk() {
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)))
                .thenReturn(ResponseEntity.ok("OK"));

        ResponseEntity<String> response = authService.get(
                "http://localhost:8080/auth/validate", "token123");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("OK", response.getBody());
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), any(), eq(String.class));
    }
}
