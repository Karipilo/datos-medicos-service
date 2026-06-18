package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.EvolucionClinica;
import datosmedicos_service.repository.EvolucionClinicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class EvolucionClinicaServiceTest {

    @Mock
    private EvolucionClinicaRepository repository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private EvolucionClinicaService service;

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
    void listarTodos_tokenValido_retornaLista() {
        mockTokenValido();
        List<EvolucionClinica> lista = List.of(new EvolucionClinica(), new EvolucionClinica());
        when(repository.findAll()).thenReturn(lista);

        List<EvolucionClinica> resultado = service.listarTodos("Bearer token123");

        assertEquals(2, resultado.size());
    }

    @Test
    void listarTodos_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listarTodos("Bearer malo"));
    }

    @Test
    void guardar_setsFechaYGuarda() {
        EvolucionClinica evolucion = new EvolucionClinica();
        evolucion.setProfesional("Dr. Ramírez");
        evolucion.setDescripcion("Evolución normal");

        when(repository.save(any(EvolucionClinica.class))).thenReturn(evolucion);

        EvolucionClinica resultado = service.guardar("Bearer token123", evolucion);

        assertNotNull(resultado);
        assertNotNull(evolucion.getFechaRegistro());
        verify(repository).save(evolucion);
    }

    @Test
    void buscarPorId_tokenValido_retornaEvolucion() {
        mockTokenValido();
        EvolucionClinica evolucion = new EvolucionClinica(
                LocalDateTime.now(), "Dr. Ramírez", "Descripción");
        when(repository.findById(1L)).thenReturn(Optional.of(evolucion));

        EvolucionClinica resultado = service.buscarPorId("Bearer token123", 1L);

        assertNotNull(resultado);
        assertEquals("Dr. Ramírez", resultado.getProfesional());
    }

    @Test
    void buscarPorId_noExiste_retornaNull() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        EvolucionClinica resultado = service.buscarPorId("Bearer token123", 99L);

        assertNull(resultado);
    }

    @Test
    void eliminar_tokenValido_eliminaEvolucion() {
        mockTokenValido();
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.eliminar("Bearer token123", 1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void actualizar_tokenValido_actualizaEvolucion() {
        mockTokenValido();
        EvolucionClinica existente = new EvolucionClinica();
        existente.setDescripcion("Descripción anterior");

        EvolucionClinica nuevaDatos = new EvolucionClinica();
        nuevaDatos.setDescripcion("Descripción actualizada");
        nuevaDatos.setObservaciones("Nueva observación");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(EvolucionClinica.class))).thenReturn(existente);

        EvolucionClinica resultado = service.actualizar("Bearer token123", 1L, nuevaDatos);

        assertNotNull(resultado);
        verify(repository).save(existente);
    }

    @Test
    void actualizar_noExiste_lanzaExcepcion() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer token123", 99L, new EvolucionClinica()));
    }

    @Test
    void actualizar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer malo", 1L, new EvolucionClinica()));
        verify(repository, never()).save(any());
    }
}
