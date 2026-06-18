package datosmedicos_service.rabbitmq;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PacienteCreadoEventTest {

    @Test
    void testNoArgsConstructor() {
        PacienteCreadoEvent event = new PacienteCreadoEvent();
        assertNotNull(event);
    }

    @Test
    void testSettersYGetters() {
        PacienteCreadoEvent event = new PacienteCreadoEvent();

        event.setId(1L);
        event.setNombre("Juan Pérez");
        event.setRut("12345678-9");

        assertEquals(1L, event.getId());
        assertEquals("Juan Pérez", event.getNombre());
        assertEquals("12345678-9", event.getRut());
    }

    @Test
    void testGetId_nulo_retornaNulo() {
        PacienteCreadoEvent event = new PacienteCreadoEvent();
        assertNull(event.getId());
    }

    @Test
    void testGetNombre_nulo_retornaNulo() {
        PacienteCreadoEvent event = new PacienteCreadoEvent();
        assertNull(event.getNombre());
    }

    @Test
    void testGetRut_nulo_retornaNulo() {
        PacienteCreadoEvent event = new PacienteCreadoEvent();
        assertNull(event.getRut());
    }
}
