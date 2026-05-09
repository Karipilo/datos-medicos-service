package datosmedicos_service.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.ActiveProfiles;
import datosmedicos_service.model.SignosVitales;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@ActiveProfiles("test")
public class SignosVitalesRepositoryTest {

    @Autowired
    private SignosVitalesRepository repository;

    @Test
    void guardarSignosVitales() {

        SignosVitales signos =
                new SignosVitales();

        signos.setPresion("120/80");
        signos.setFrecuencia(72);
        signos.setTemperatura(36.5);
        signos.setSaturacion(98);
        signos.setFecha("2026-05-03");
        signos.setProfesional("Dr. Carlos");

        SignosVitales guardado =
                repository.save(signos);

        assertNotNull(
                guardado.getId()
        );

        assertEquals(
                "120/80",
                guardado.getPresion()
        );
    }
}