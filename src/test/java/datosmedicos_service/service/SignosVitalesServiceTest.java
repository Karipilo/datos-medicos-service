package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.repository.FichaClinicaRepository;
import datosmedicos_service.repository.SignosVitalesRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class SignosVitalesServiceTest {

    @Mock
    private SignosVitalesRepository repository;

    @Mock
    private AuthService authService;

    @Mock
    private FichaClinicaRepository fichaRepository;

    @InjectMocks
    private SignosVitalesService service;

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
    void guardarSignosVitales_sinToken() {
        SignosVitales signos = new SignosVitales();
        signos.setPresion("120/80");
        when(repository.save(signos)).thenReturn(signos);

        SignosVitales resultado = service.guardar(signos);

        assertNotNull(resultado);
        assertEquals("120/80", resultado.getPresion());
        verify(repository, times(1)).save(signos);
    }

    @Test
    void guardar_conToken_tokenValido() {
        mockTokenValido();
        SignosVitales signos = new SignosVitales();
        signos.setPresion("110/70");
        when(repository.save(signos)).thenReturn(signos);

        SignosVitales resultado = service.guardar("Bearer token123", signos);

        assertNotNull(resultado);
        verify(repository).save(signos);
    }

    @Test
    void guardar_conToken_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.guardar("Bearer malo", new SignosVitales()));
        verify(repository, never()).save(any());
    }

    @Test
    void guardar_conFichaId_asignaFichaYFecha() {
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        SignosVitales signos = new SignosVitales();
        signos.setPresion("130/85");

        when(fichaRepository.findById(1L)).thenReturn(Optional.of(ficha));
        when(repository.save(any(SignosVitales.class))).thenReturn(signos);

        SignosVitales resultado = service.guardar(1L, signos);

        assertNotNull(resultado);
        assertNotNull(signos.getFicha());
        assertNotNull(signos.getFechaRegistro());
        verify(repository).save(signos);
    }

    @Test
    void guardar_conFichaId_fichaNoExiste_lanzaExcepcion() {
        when(fichaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.guardar(99L, new SignosVitales()));
        verify(repository, never()).save(any());
    }

    @Test
    void listarTodos_tokenValido_retornaLista() {
        mockTokenValido();
        List<SignosVitales> lista = List.of(new SignosVitales(), new SignosVitales());
        when(repository.findAll()).thenReturn(lista);

        List<SignosVitales> resultado = service.listarTodos("Bearer token123");

        assertEquals(2, resultado.size());
    }

    @Test
    void listarTodos_tokenInvalido_lanzaExcepcion() {
        mockTokenInvalido();
        assertThrows(RuntimeException.class,
                () -> service.listarTodos("Bearer malo"));
    }

    @Test
    void listarPorFicha_retornaLista() {
        List<SignosVitales> lista = List.of(new SignosVitales());
        when(repository.findByFichaId(1L)).thenReturn(lista);

        List<SignosVitales> resultado = service.listarPorFicha(1L);

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorId_tokenValido_retornaSignos() {
        mockTokenValido();
        SignosVitales signos = new SignosVitales();
        signos.setPresion("120/80");
        when(repository.findById(1L)).thenReturn(Optional.of(signos));

        SignosVitales resultado = service.buscarPorId("Bearer token123", 1L);

        assertNotNull(resultado);
        assertEquals("120/80", resultado.getPresion());
    }

    @Test
    void buscarPorId_noExiste_retornaNull() {
        mockTokenValido();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        SignosVitales resultado = service.buscarPorId("Bearer token123", 99L);

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
}
