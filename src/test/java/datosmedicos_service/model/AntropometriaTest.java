package datosmedicos_service.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AntropometriaTest {

    @Test
    void testNoArgsConstructor() {
        Antropometria a = new Antropometria();
        assertNotNull(a);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 15, 10, 0);
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");

        Antropometria a = new Antropometria(1L, 80.0, 1.75, fecha, "Dr. López", ficha);

        assertEquals(1L, a.getId());
        assertEquals(80.0, a.getPeso());
        assertEquals(1.75, a.getAltura());
        assertEquals(fecha, a.getFechaRegistro());
        assertEquals("Dr. López", a.getProfesional());
        assertEquals(ficha, a.getFicha());
    }

    @Test
    void testGettersSetters() {
        Antropometria a = new Antropometria();
        LocalDateTime fecha = LocalDateTime.of(2024, 6, 1, 9, 30);

        a.setId(2L);
        a.setPeso(65.5);
        a.setAltura(1.65);
        a.setFechaRegistro(fecha);
        a.setProfesional("Dra. Martínez");

        assertEquals(2L, a.getId());
        assertEquals(65.5, a.getPeso());
        assertEquals(1.65, a.getAltura());
        assertEquals(fecha, a.getFechaRegistro());
        assertEquals("Dra. Martínez", a.getProfesional());
    }

    @Test
    void testSetFicha() {
        Antropometria a = new Antropometria();
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("11111111-1");
        a.setFicha(ficha);
        assertEquals(ficha, a.getFicha());
    }

    @Test
    void testEquals_mismoObjeto() {
        Antropometria a = new Antropometria();
        a.setId(1L);
        assertEquals(a, a);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        Antropometria a = new Antropometria();
        assertNotEquals(a, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        Antropometria a = new Antropometria();
        assertNotEquals(a, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 1, 0, 0);
        Antropometria a1 = new Antropometria(1L, 70.0, 1.75, fecha, "Dr. A", null);
        Antropometria a2 = new Antropometria(1L, 70.0, 1.75, fecha, "Dr. A", null);
        assertEquals(a1, a2);
    }

    @Test
    void testEquals_diferenteId_retornaFalso() {
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 1, 0, 0);
        Antropometria a1 = new Antropometria(1L, 70.0, 1.75, fecha, "Dr. A", null);
        Antropometria a2 = new Antropometria(2L, 70.0, 1.75, fecha, "Dr. A", null);
        assertNotEquals(a1, a2);
    }

    @Test
    void testEquals_diferentePeso_retornaFalso() {
        Antropometria a1 = new Antropometria(1L, 70.0, 1.75, null, "Dr. A", null);
        Antropometria a2 = new Antropometria(1L, 80.0, 1.75, null, "Dr. A", null);
        assertNotEquals(a1, a2);
    }

    @Test
    void testEquals_campoNulo_retornaFalso() {
        Antropometria a1 = new Antropometria();
        a1.setId(1L);
        a1.setPeso(null);
        Antropometria a2 = new Antropometria();
        a2.setId(1L);
        a2.setPeso(70.0);
        assertNotEquals(a1, a2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 1, 0, 0);
        Antropometria a1 = new Antropometria(1L, 70.0, 1.75, fecha, "Dr. A", null);
        Antropometria a2 = new Antropometria(1L, 70.0, 1.75, fecha, "Dr. A", null);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        Antropometria a = new Antropometria();
        a.setId(5L);
        a.setPeso(60.0);
        assertDoesNotThrow(() -> a.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        Antropometria a = new Antropometria();
        a.setId(1L);
        a.setProfesional("Dr. X");
        String str = a.toString();
        assertNotNull(str);
        assertTrue(str.contains("Antropometria"));
    }
}
