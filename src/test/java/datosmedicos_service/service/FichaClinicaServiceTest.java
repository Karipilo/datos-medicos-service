package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.FichaClinicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FichaClinicaServiceTest {

    @Mock
    private FichaClinicaRepository repository;

    @InjectMocks
    private FichaClinicaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar() {

        List<FichaClinica> fichas =
                List.of(new FichaClinica());

        when(repository.findAll())
                .thenReturn(fichas);

        List<FichaClinica> resultado =
                service.listar();

        assertEquals(1, resultado.size());

        verify(repository)
                .findAll();
    }

    @Test
    void guardarNuevaFicha() {

        FichaClinica ficha =
                new FichaClinica();

        ficha.setRutPaciente("12345678-9");

        when(repository.findByRutPaciente(
                ficha.getRutPaciente()))
                .thenReturn(Optional.empty());

        when(repository.save(ficha))
                .thenReturn(ficha);

        FichaClinica resultado =
                service.guardar(ficha);

        assertNotNull(resultado);

        verify(repository)
                .save(ficha);
    }

    @Test
    void guardarFichaExistente() {

        FichaClinica ficha =
                new FichaClinica();

        ficha.setRutPaciente("12345678-9");

        when(repository.findByRutPaciente(
                ficha.getRutPaciente()))
                .thenReturn(Optional.of(ficha));

        FichaClinica resultado =
                service.guardar(ficha);

        assertNotNull(resultado);

        verify(repository, never())
                .save(any());
    }

    @Test
    void buscarPorRut() {

        FichaClinica ficha =
                new FichaClinica();

        ficha.setRutPaciente("12345678-9");

        when(repository.findByRutPaciente(
                "12345678-9"))
                .thenReturn(Optional.of(ficha));

        FichaClinica resultado =
                service.buscarPorRut(
                        "12345678-9"
                );

        assertNotNull(resultado);

        assertEquals(
                "12345678-9",
                resultado.getRutPaciente()
        );
    }

    @Test
    void actualizarFicha() {

        FichaClinica ficha =
                new FichaClinica();

        ficha.setRutPaciente("12345678-9");

        FichaClinica nuevosDatos =
                new FichaClinica();

        nuevosDatos.setDiagnostico("Diabetes");
        nuevosDatos.setAlergias("Penicilina");
        nuevosDatos.setObservaciones("Control");
        nuevosDatos.setEdad(70);
        nuevosDatos.setGenero("Masculino");

        when(repository.findByRutPaciente(
                "12345678-9"))
                .thenReturn(Optional.of(ficha));

        when(repository.save(any()))
                .thenReturn(ficha);

        FichaClinica resultado =
                service.actualizar(
                        "12345678-9",
                        nuevosDatos
                );

        assertNotNull(resultado);

        verify(repository)
                .save(any(FichaClinica.class));
    }
}