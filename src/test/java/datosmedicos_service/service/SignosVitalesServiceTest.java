package datosmedicos_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.repository.SignosVitalesRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SignosVitalesServiceTest {

    @Mock
    private SignosVitalesRepository repository;

    @InjectMocks
    private SignosVitalesService service;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarSignosVitales() {

        SignosVitales signos =
                new SignosVitales();

        signos.setPresion("120/80");

        when(
                repository.save(signos)
        ).thenReturn(signos);

        SignosVitales resultado =
                service.guardar(signos);

        assertNotNull(resultado);

        assertEquals(
                "120/80",
                resultado.getPresion()
        );

        verify(repository, times(1))
                .save(signos);
    }
}