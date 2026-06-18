package datosmedicos_service.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class SignosVitalesTest {

    @Test
    void testGettersSetters() {

        SignosVitales s = new SignosVitales();

        s.setId(1L);
        s.setPresion("120/80");
        s.setFrecuencia(70);
        s.setTemperatura(36.5);
        s.setSaturacion(98);

        assertEquals(1L, s.getId());
        assertEquals("120/80", s.getPresion());
        assertEquals(70, s.getFrecuencia());
        assertEquals(36.5, s.getTemperatura());
        assertEquals(98, s.getSaturacion());
    }

    @Test
    void testGettersSetters_camposAdicionales() {
        SignosVitales s = new SignosVitales();
        LocalDateTime fecha = LocalDateTime.of(2024, 4, 15, 8, 30);

        s.setProfesional("Enfermera Lopez");
        s.setFechaRegistro(fecha);

        assertEquals("Enfermera Lopez", s.getProfesional());
        assertEquals(fecha, s.getFechaRegistro());
    }

    @Test
    void testSetFicha() {
        SignosVitales s = new SignosVitales();
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        s.setFicha(ficha);
        assertEquals(ficha, s.getFicha());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime fecha = LocalDateTime.of(2024, 4, 15, 8, 30);

        SignosVitales s = new SignosVitales(
                1L, "130/85", 75, 37.0, 97,
                "Dr. Lopez", fecha, null);

        assertEquals(1L, s.getId());
        assertEquals("130/85", s.getPresion());
        assertEquals(75, s.getFrecuencia());
        assertEquals(37.0, s.getTemperatura());
        assertEquals(97, s.getSaturacion());
        assertEquals("Dr. Lopez", s.getProfesional());
        assertEquals(fecha, s.getFechaRegistro());
        assertNull(s.getFicha());
    }

    @Test
    void testEquals_mismoObjeto() {
        SignosVitales s = new SignosVitales();
        s.setId(1L);
        assertEquals(s, s);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        SignosVitales s = new SignosVitales();
        assertNotEquals(s, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        SignosVitales s = new SignosVitales();
        assertNotEquals(s, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        LocalDateTime fecha = LocalDateTime.of(2024, 4, 15, 8, 30);
        SignosVitales s1 = new SignosVitales(1L, "120/80", 72, 36.5, 98, "Dr. A", fecha, null);
        SignosVitales s2 = new SignosVitales(1L, "120/80", 72, 36.5, 98, "Dr. A", fecha, null);
        assertEquals(s1, s2);
    }

    @Test
    void testEquals_diferentePresion_retornaFalso() {
        SignosVitales s1 = new SignosVitales(1L, "120/80", 72, 36.5, 98, "Dr. A", null, null);
        SignosVitales s2 = new SignosVitales(1L, "130/90", 72, 36.5, 98, "Dr. A", null, null);
        assertNotEquals(s1, s2);
    }

    @Test
    void testEquals_campoNuloVsNoNulo() {
        SignosVitales s1 = new SignosVitales();
        s1.setId(1L);
        s1.setPresion(null);
        SignosVitales s2 = new SignosVitales();
        s2.setId(1L);
        s2.setPresion("120/80");
        assertNotEquals(s1, s2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        LocalDateTime fecha = LocalDateTime.of(2024, 4, 15, 8, 30);
        SignosVitales s1 = new SignosVitales(1L, "120/80", 72, 36.5, 98, "Dr. A", fecha, null);
        SignosVitales s2 = new SignosVitales(1L, "120/80", 72, 36.5, 98, "Dr. A", fecha, null);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        SignosVitales s = new SignosVitales();
        s.setId(1L);
        s.setPresion("120/80");
        assertDoesNotThrow(() -> s.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        SignosVitales s = new SignosVitales();
        s.setPresion("120/80");
        String str = s.toString();
        assertNotNull(str);
        assertTrue(str.contains("SignosVitales"));
    }
}
