package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.repository.ControlMedicoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class ControlMedicoServiceTest {

    @Mock
    private ControlMedicoRepository repository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private ControlMedicoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "AUTH_URL", "http://localhost:8080/auth/validate");
    }

    private void mockTokenValido() {
        when(authService.get(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok("OK"));
    }

    private void mockTokenInvalido() {
        when(authService.get(anyString(), anyString()))
                .thenThrow(new RuntimeException("Token inválido"));
    }

    @Test
    void validarToken_tokenValido_retornaTrue() {
        mockTokenValido();
        assertTrue(service.validarToken("Bearer token123"));
    }

    @Test
    void validarToken_tokenInvalido_retornaFalse() {
        mockTokenInvalido();
        assertFalse(service.validarToken("Bearer token-malo"));
    }

    @Test
    void listarTodos_tokenValido_retornaLista() {
        mockTokenValido();
        List<ControlMedico> lista = List.of(new ControlMedico(), new ControlMedico());
        when(repository.findAll()).thenReturn(lista);

        List<ControlMedico> resultado = service.listarTodos("Bearer token123");

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void listarTodos_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listarTodos("Bearer token-malo"));
        verify(repository, never()).findAll();
    }

    @Test
    void guardar_tokenValido_guardaControl() {
        mockTokenValido();
        ControlMedico control = new ControlMedico("2024-01-01", "General", "Dr. Pérez", "Sin novedades");
        when(repository.save(control)).thenReturn(control);

        ControlMedico resultado = service.guardar("Bearer token123", control);

        assertNotNull(resultado);
        assertEquals("Dr. Pérez", resultado.getProfesional());
        verify(repository).save(control);
    }

    @Test
    void guardar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        ControlMedico control = new ControlMedico();
        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer malo", control));
        verify(repository, never()).save(any());
    }

    @Test
    void buscarPorId_tokenValido_retornaControl() {
        mockTokenValido();
        ControlMedico control = new ControlMedico();
        when(repository.findById(1L)).thenReturn(Optional.of(control));

        ControlMedico resultado = service.buscarPorId("Bearer token123", 1L);

        assertNotNull(resultado);
    }

    @Test
    void buscarPorId_noExiste_retornaNull() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ControlMedico resultado = service.buscarPorId("Bearer token123", 99L);

        assertNull(resultado);
    }

    @Test
    void eliminar_tokenValido_eliminaControl() {
        mockTokenValido();
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.eliminar("Bearer token123", 1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void eliminar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.eliminar("Bearer malo", 1L));
        verify(repository, never()).deleteById(any());
    }
}
