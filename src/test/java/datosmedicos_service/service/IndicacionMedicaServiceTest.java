package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.IndicacionMedica;
import datosmedicos_service.repository.IndicacionMedicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class IndicacionMedicaServiceTest {

    @Mock
    private IndicacionMedicaRepository repository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private IndicacionMedicaService service;

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
        List<IndicacionMedica> lista = List.of(
                new IndicacionMedica(LocalDateTime.now(), "Dr. A", "Reposo"),
                new IndicacionMedica(LocalDateTime.now(), "Dr. B", "Dieta"));
        when(repository.findAll()).thenReturn(lista);

        List<IndicacionMedica> resultado = service.listarTodos("Bearer token123");

        assertEquals(2, resultado.size());
    }

    @Test
    void listarTodos_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listarTodos("Bearer malo"));
    }

    @Test
    void guardar_tokenValido_guardaIndicacion() {
        mockTokenValido();
        IndicacionMedica indicacion = new IndicacionMedica();
        indicacion.setIndicacion("Reposo absoluto");
        indicacion.setProfesional("Dr. Soto");

        when(repository.save(any(IndicacionMedica.class))).thenReturn(indicacion);

        IndicacionMedica resultado = service.guardar("Bearer token123", indicacion);

        assertNotNull(resultado);
        assertNotNull(indicacion.getFechaRegistro());
        assertEquals("Reposo absoluto", resultado.getIndicacion());
        verify(repository).save(indicacion);
    }

    @Test
    void guardar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer malo", new IndicacionMedica()));
        verify(repository, never()).save(any());
    }

    @Test
    void buscarPorId_tokenValido_retornaIndicacion() {
        mockTokenValido();
        IndicacionMedica indicacion = new IndicacionMedica(
                LocalDateTime.now(), "Dr. Soto", "Dieta blanda");
        when(repository.findById(1L)).thenReturn(Optional.of(indicacion));

        IndicacionMedica resultado = service.buscarPorId("Bearer token123", 1L);

        assertNotNull(resultado);
        assertEquals("Dr. Soto", resultado.getProfesional());
    }

    @Test
    void buscarPorId_noExiste_retornaNull() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        IndicacionMedica resultado = service.buscarPorId("Bearer token123", 99L);

        assertNull(resultado);
    }

    @Test
    void eliminar_tokenValido_elimina() {
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

    @Test
    void actualizar_tokenValido_actualizaIndicacion() {
        mockTokenValido();
        IndicacionMedica existente = new IndicacionMedica();
        existente.setIndicacion("Reposo");

        IndicacionMedica nuevaDatos = new IndicacionMedica();
        nuevaDatos.setIndicacion("Reposo absoluto por 5 días");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(IndicacionMedica.class))).thenReturn(existente);

        IndicacionMedica resultado = service.actualizar("Bearer token123", 1L, nuevaDatos);

        assertNotNull(resultado);
        verify(repository).save(existente);
    }

    @Test
    void actualizar_noExiste_lanzaExcepcion() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer token123", 99L, new IndicacionMedica()));
    }
}
