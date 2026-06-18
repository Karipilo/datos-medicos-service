package datosmedicos_service.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MedicamentoTest {

    @Test
    void testGettersSetters() {

        Medicamento m = new Medicamento();

        m.setId(1L);
        m.setNombre("Paracetamol");
        m.setDosis("500mg");
        m.setFrecuencia("8 horas");
        m.setProfesional("Dr. House");
        m.setObservaciones("Tomar con agua");

        assertEquals(1L, m.getId());
        assertEquals("Paracetamol", m.getNombre());
        assertEquals("500mg", m.getDosis());
        assertEquals("8 horas", m.getFrecuencia());
        assertEquals("Dr. House", m.getProfesional());
        assertEquals("Tomar con agua", m.getObservaciones());
    }

    @Test
    void testGettersSetters_camposAdicionales() {
        Medicamento m = new Medicamento();
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 10, 9, 0);

        m.setFechaRegistro(fecha);
        m.setDiasTratamiento(7);

        assertEquals(fecha, m.getFechaRegistro());
        assertEquals(7, m.getDiasTratamiento());
    }

    @Test
    void testSetFicha() {
        Medicamento m = new Medicamento();
        m.setFicha("FICHA-001");
        assertEquals("FICHA-001", m.getFicha());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime fecha = LocalDateTime.of(2024, 1, 10, 9, 0);

        Medicamento m = new Medicamento(
                1L, "Ibuprofeno", "400mg", "12 horas",
                "Tomar con comida", fecha, 5, "Dr. Soto", "FICHA-001");

        assertEquals(1L, m.getId());
        assertEquals("Ibuprofeno", m.getNombre());
        assertEquals("400mg", m.getDosis());
        assertEquals("12 horas", m.getFrecuencia());
        assertEquals("Tomar con comida", m.getObservaciones());
        assertEquals(fecha, m.getFechaRegistro());
        assertEquals(5, m.getDiasTratamiento());
        assertEquals("Dr. Soto", m.getProfesional());
        assertEquals("FICHA-001", m.getFicha());
    }

    @Test
    void testEquals_mismoObjeto() {
        Medicamento m = new Medicamento();
        m.setId(1L);
        assertEquals(m, m);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        Medicamento m = new Medicamento();
        assertNotEquals(m, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        Medicamento m = new Medicamento();
        assertNotEquals(m, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        Medicamento m1 = new Medicamento(1L, "Paracetamol", "500mg", "8h", "Obs", null, 3, "Dr.", "F1");
        Medicamento m2 = new Medicamento(1L, "Paracetamol", "500mg", "8h", "Obs", null, 3, "Dr.", "F1");
        assertEquals(m1, m2);
    }

    @Test
    void testEquals_diferenteNombre_retornaFalso() {
        Medicamento m1 = new Medicamento(1L, "Paracetamol", null, null, null, null, null, null, null);
        Medicamento m2 = new Medicamento(1L, "Ibuprofeno", null, null, null, null, null, null, null);
        assertNotEquals(m1, m2);
    }

    @Test
    void testEquals_campoNuloVsNoNulo() {
        Medicamento m1 = new Medicamento();
        m1.setId(1L);
        m1.setNombre(null);
        Medicamento m2 = new Medicamento();
        m2.setId(1L);
        m2.setNombre("Paracetamol");
        assertNotEquals(m1, m2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        Medicamento m1 = new Medicamento(1L, "Para", "500mg", "8h", "Obs", null, 3, "Dr.", "F1");
        Medicamento m2 = new Medicamento(1L, "Para", "500mg", "8h", "Obs", null, 3, "Dr.", "F1");
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        Medicamento m = new Medicamento();
        assertDoesNotThrow(() -> m.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        Medicamento m = new Medicamento();
        m.setNombre("Paracetamol");
        String str = m.toString();
        assertNotNull(str);
        assertTrue(str.contains("Medicamento"));
    }
}
