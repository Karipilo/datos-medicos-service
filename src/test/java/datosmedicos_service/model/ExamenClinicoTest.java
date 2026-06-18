package datosmedicos_service.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ExamenClinicoTest {

    @Test
    void testNoArgsConstructor() {
        ExamenClinico e = new ExamenClinico();
        assertNotNull(e);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime fecha = LocalDateTime.of(2024, 3, 10, 8, 0);

        ExamenClinico e = new ExamenClinico(
                1L, "Hemograma", fecha, "Completado",
                "Dr. Pérez", "Observación", "Normal", null);

        assertEquals(1L, e.getId());
        assertEquals("Hemograma", e.getNombre());
        assertEquals(fecha, e.getFechaRegistro());
        assertEquals("Completado", e.getEstado());
        assertEquals("Dr. Pérez", e.getProfesional());
        assertEquals("Observación", e.getObservacion());
        assertEquals("Normal", e.getResultado());
        assertNull(e.getFicha());
    }

    @Test
    void testGettersSetters() {
        ExamenClinico e = new ExamenClinico();
        LocalDateTime fecha = LocalDateTime.of(2024, 6, 5, 14, 30);

        e.setId(3L);
        e.setNombre("TAC");
        e.setFechaRegistro(fecha);
        e.setEstado("Pendiente");
        e.setProfesional("Dra. Gómez");
        e.setObservacion("Urgente");
        e.setResultado("Sin hallazgos");

        assertEquals(3L, e.getId());
        assertEquals("TAC", e.getNombre());
        assertEquals(fecha, e.getFechaRegistro());
        assertEquals("Pendiente", e.getEstado());
        assertEquals("Dra. Gómez", e.getProfesional());
        assertEquals("Urgente", e.getObservacion());
        assertEquals("Sin hallazgos", e.getResultado());
    }

    @Test
    void testSetFicha() {
        ExamenClinico e = new ExamenClinico();
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        e.setFicha(ficha);
        assertEquals(ficha, e.getFicha());
    }

    @Test
    void testEquals_mismoObjeto() {
        ExamenClinico e = new ExamenClinico();
        e.setId(1L);
        assertEquals(e, e);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        ExamenClinico e = new ExamenClinico();
        assertNotEquals(e, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        ExamenClinico e = new ExamenClinico();
        assertNotEquals(e, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        LocalDateTime fecha = LocalDateTime.of(2024, 3, 10, 8, 0);
        ExamenClinico e1 = new ExamenClinico(1L, "TAC", fecha, "OK", "Dr.", "Obs", "Res", null);
        ExamenClinico e2 = new ExamenClinico(1L, "TAC", fecha, "OK", "Dr.", "Obs", "Res", null);
        assertEquals(e1, e2);
    }

    @Test
    void testEquals_diferenteId_retornaFalso() {
        ExamenClinico e1 = new ExamenClinico(1L, "TAC", null, "OK", "Dr.", "Obs", "Res", null);
        ExamenClinico e2 = new ExamenClinico(2L, "TAC", null, "OK", "Dr.", "Obs", "Res", null);
        assertNotEquals(e1, e2);
    }

    @Test
    void testEquals_diferenteNombre_retornaFalso() {
        ExamenClinico e1 = new ExamenClinico(1L, "TAC", null, "OK", "Dr.", null, null, null);
        ExamenClinico e2 = new ExamenClinico(1L, "ECO", null, "OK", "Dr.", null, null, null);
        assertNotEquals(e1, e2);
    }

    @Test
    void testEquals_campoNuloVsNoNulo() {
        ExamenClinico e1 = new ExamenClinico();
        e1.setId(1L);
        e1.setNombre(null);
        ExamenClinico e2 = new ExamenClinico();
        e2.setId(1L);
        e2.setNombre("Hemograma");
        assertNotEquals(e1, e2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        LocalDateTime fecha = LocalDateTime.of(2024, 3, 10, 8, 0);
        ExamenClinico e1 = new ExamenClinico(1L, "TAC", fecha, "OK", "Dr.", "Obs", "Res", null);
        ExamenClinico e2 = new ExamenClinico(1L, "TAC", fecha, "OK", "Dr.", "Obs", "Res", null);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        ExamenClinico e = new ExamenClinico();
        e.setId(1L);
        e.setNombre("Hemograma");
        assertDoesNotThrow(() -> e.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        ExamenClinico e = new ExamenClinico();
        e.setNombre("Radiografía");
        String str = e.toString();
        assertNotNull(str);
        assertTrue(str.contains("ExamenClinico"));
    }
}
