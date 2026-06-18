package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.Antropometria;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.AntropometriaRepository;
import datosmedicos_service.repository.FichaClinicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AntropometriaServiceTest {

    @Mock
    private AntropometriaRepository repository;

    @Mock
    private FichaClinicaRepository fichaRepository;

    @InjectMocks
    private AntropometriaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardar_conFichaExistente_retornaAntropometria() {
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");

        Antropometria antro = new Antropometria();
        antro.setPeso(70.0);
        antro.setAltura(1.75);

        when(fichaRepository.findById(1L))
                .thenReturn(Optional.of(ficha));
        when(repository.save(any(Antropometria.class)))
                .thenReturn(antro);

        Antropometria resultado = service.guardar(1L, antro);

        assertNotNull(resultado);
        assertEquals(70.0, resultado.getPeso());
        verify(repository).save(any(Antropometria.class));
    }

    @Test
    void guardar_fichaNoExiste_lanzaExcepcion() {
        when(fichaRepository.findById(99L))
                .thenReturn(Optional.empty());

        Antropometria antro = new Antropometria();

        assertThrows(RuntimeException.class,
                () -> service.guardar(99L, antro));

        verify(repository, never()).save(any());
    }

    @Test
    void listarPorFicha_retornaLista() {
        List<Antropometria> lista = List.of(
                new Antropometria(), new Antropometria());

        when(repository.findByFichaId(1L))
                .thenReturn(lista);

        List<Antropometria> resultado = service.listarPorFicha(1L);

        assertEquals(2, resultado.size());
        verify(repository).findByFichaId(1L);
    }

    @Test
    void listarPorFicha_listaVacia_retornaVacio() {
        when(repository.findByFichaId(1L))
                .thenReturn(List.of());

        List<Antropometria> resultado = service.listarPorFicha(1L);

        assertTrue(resultado.isEmpty());
    }
}
