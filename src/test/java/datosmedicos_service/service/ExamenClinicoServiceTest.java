package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import datosmedicos_service.dto.ExamenClinicoRequest;
import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.ExamenClinicoRepository;
import datosmedicos_service.repository.FichaClinicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class ExamenClinicoServiceTest {

    @Mock
    private ExamenClinicoRepository repository;

    @Mock
    private AuthService authService;

    @Mock
    private FichaClinicaRepository fichaRepository;

    @InjectMocks
    private ExamenClinicoService service;

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
        assertFalse(service.validarToken("Bearer malo"));
    }

    @Test
    void listarTodos_tokenValido_conFichaYFecha_retornaLista() {
        mockTokenValido();

        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");

        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("Hemograma");
        examen.setEstado("Pendiente");
        examen.setProfesional("Dr. A");
        examen.setFechaRegistro(LocalDateTime.now());
        examen.setFicha(ficha);
        examen.setObservacion("Sin obs");
        examen.setResultado("Normal");

        when(repository.findAll()).thenReturn(List.of(examen));

        List<ExamenClinicoRequest> resultado = service.listarTodos("Bearer token123");

        assertEquals(1, resultado.size());
        assertEquals("Hemograma", resultado.get(0).getNombre());
        assertEquals("Pendiente", resultado.get(0).getEstado());
        verify(repository).findAll();
    }

    @Test
    void listarTodos_conFichaYFechaNulas_retornaLista() {
        mockTokenValido();

        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("TAC");
        examen.setEstado("Completado");
        // ficha null y fechaRegistro null -> cubre las ramas null del mapeo

        when(repository.findAll()).thenReturn(List.of(examen));

        List<ExamenClinicoRequest> resultado = service.listarTodos("Bearer token123");

        assertEquals(1, resultado.size());
        assertNull(resultado.get(0).getFicha());
        assertNull(resultado.get(0).getFechaRegistro());
    }

    @Test
    void listarTodos_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listarTodos("Bearer malo"));
        verify(repository, never()).findAll();
    }

    @Test
    void guardar_tokenValido_fichaExiste_creaExamen() {
        mockTokenValido();

        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        when(fichaRepository.findById(1L)).thenReturn(Optional.of(ficha));

        ExamenClinicoRequest request = new ExamenClinicoRequest(
                null, "Eco abdominal", null, "Pendiente", "Dr. Soto",
                "Sin observaciones", null, 1L);

        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("Eco abdominal");
        examen.setEstado("Pendiente");
        when(repository.save(any(ExamenClinico.class))).thenReturn(examen);

        ExamenClinico resultado = service.guardar("Bearer token123", request);

        assertNotNull(resultado);
        assertEquals("Eco abdominal", resultado.getNombre());
        verify(fichaRepository).findById(1L);
        verify(repository).save(any(ExamenClinico.class));
    }

    @Test
    void guardar_fichaNoExiste_lanzaExcepcion() {
        mockTokenValido();
        when(fichaRepository.findById(99L)).thenReturn(Optional.empty());

        ExamenClinicoRequest request = new ExamenClinicoRequest(
                null, "Eco", null, "Pendiente", "Dr. B", null, null, 99L);

        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer token123", request));
        verify(repository, never()).save(any());
    }

    @Test
    void guardar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        ExamenClinicoRequest request = new ExamenClinicoRequest();
        request.setFicha(1L);

        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer malo", request));
        verify(repository, never()).save(any());
    }

    @Test
    void buscarPorId_tokenValido_retornaExamen() {
        mockTokenValido();
        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("Hemograma");
        when(repository.findById(1L)).thenReturn(Optional.of(examen));

        ExamenClinico resultado = service.buscarPorId("Bearer token123", 1L);

        assertNotNull(resultado);
        assertEquals("Hemograma", resultado.getNombre());
    }

    @Test
    void buscarPorId_noExiste_retornaNull() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ExamenClinico resultado = service.buscarPorId("Bearer token123", 99L);

        assertNull(resultado);
    }

    @Test
    void buscarPorId_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.buscarPorId("Bearer malo", 1L));
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
    void actualizar_tokenValido_actualizaExamen() {
        mockTokenValido();
        ExamenClinico existente = new ExamenClinico();
        existente.setEstado("Pendiente");

        ExamenClinicoRequest nuevoDatos = new ExamenClinicoRequest();
        nuevoDatos.setEstado("Completado");
        nuevoDatos.setResultado("Todo normal");
        nuevoDatos.setObservacion("Sin hallazgos");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(ExamenClinico.class))).thenReturn(existente);

        ExamenClinico resultado = service.actualizar("Bearer token123", 1L, nuevoDatos);

        assertNotNull(resultado);
        verify(repository).save(existente);
    }

    @Test
    void actualizar_noExiste_lanzaExcepcion() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer token123", 99L, new ExamenClinicoRequest()));
    }

    @Test
    void actualizar_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.actualizar("Bearer malo", 1L, new ExamenClinicoRequest()));
        verify(repository, never()).save(any());
    }
}
