package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.Medicamento;
import datosmedicos_service.repository.MedicamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepository repository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private MedicamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(
                service,
                "AUTH_URL",
                "http://localhost:8080/auth/validate");

        ReflectionTestUtils.setField(
                service,
                "authService",
                authService);
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
    void listar_tokenValido_retornaLista() {
        mockTokenValido();
        Medicamento m1 = new Medicamento();
        m1.setNombre("Paracetamol");
        Medicamento m2 = new Medicamento();
        m2.setNombre("Ibuprofeno");

        when(repository.findAll()).thenReturn(List.of(m1, m2));

        List<Medicamento> resultado = service.listar("Bearer token123");

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void listar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listar("Bearer malo"));
        verify(repository, never()).findAll();
    }

    @Test
    void guardar_tokenValido_guardaMedicamento() {
        mockTokenValido();
        Medicamento med = new Medicamento();
        med.setNombre("Amoxicilina");
        med.setDosis("500mg");

        when(repository.save(any(Medicamento.class))).thenReturn(med);

        Medicamento resultado = service.guardar("Bearer token123", med);

        assertNotNull(resultado);
        assertNotNull(med.getFechaRegistro());
        assertEquals("Amoxicilina", resultado.getNombre());
        verify(repository).save(med);
    }

    @Test
    void guardar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer malo", new Medicamento()));
        verify(repository, never()).save(any());
    }

    @Test
    void actualizar_tokenValido_actualizaMedicamento() {
        mockTokenValido();
        Medicamento existente = new Medicamento();
        existente.setNombre("Paracetamol");
        existente.setDosis("500mg");

        Medicamento nuevoDatos = new Medicamento();
        nuevoDatos.setNombre("Paracetamol 1g");
        nuevoDatos.setDosis("1000mg");
        nuevoDatos.setFrecuencia("Cada 8 horas");
        nuevoDatos.setObservaciones("Con alimentos");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Medicamento.class))).thenReturn(existente);

        Medicamento resultado = service.actualizar("Bearer token123", 1L, nuevoDatos);

        assertNotNull(resultado);
        verify(repository).save(existente);
    }

    @Test
    void actualizar_noExiste_lanzaExcepcion() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer token123", 99L, new Medicamento()));
    }

    @Test
    void actualizar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer malo", 1L, new Medicamento()));
        verify(repository, never()).save(any());
    }
}
