package datosmedicos_service.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExamenClinicoRequestTest {

    @Test
    void testConstructorAllArgs() {

        ExamenClinicoRequest dto =
                new ExamenClinicoRequest(
                        1L,
                        "Hemograma",
                        "2026-06-18",
                        "Pendiente",
                        "Dr. Perez",
                        "Observacion",
                        "Resultado",
                        5L);

        assertEquals(1L, dto.getId());
        assertEquals("Hemograma", dto.getNombre());
        assertEquals("2026-06-18", dto.getFechaRegistro());
        assertEquals("Pendiente", dto.getEstado());
        assertEquals("Dr. Perez", dto.getProfesional());
        assertEquals("Observacion", dto.getObservacion());
        assertEquals("Resultado", dto.getResultado());
        assertEquals(5L, dto.getFicha());
    }

    @Test
    void testConstructorNoArgsYSetters() {

        ExamenClinicoRequest dto = new ExamenClinicoRequest();

        dto.setId(2L);
        dto.setNombre("Radiografia");
        dto.setFechaRegistro("2026-06-18");
        dto.setEstado("Completado");
        dto.setProfesional("Dr. Ramirez");
        dto.setObservacion("Sin hallazgos");
        dto.setResultado("Normal");
        dto.setFicha(8L);

        assertEquals(2L, dto.getId());
        assertEquals("Radiografia", dto.getNombre());
        assertEquals("2026-06-18", dto.getFechaRegistro());
        assertEquals("Completado", dto.getEstado());
        assertEquals("Dr. Ramirez", dto.getProfesional());
        assertEquals("Sin hallazgos", dto.getObservacion());
        assertEquals("Normal", dto.getResultado());
        assertEquals(8L, dto.getFicha());
    }

    @Test
    void testEquals_mismoObjeto() {
        ExamenClinicoRequest dto = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-01", "Pendiente",
                "Dr. A", "Obs", "Res", 1L);
        assertEquals(dto, dto);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        ExamenClinicoRequest dto = new ExamenClinicoRequest();
        assertNotEquals(dto, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        ExamenClinicoRequest dto = new ExamenClinicoRequest();
        assertNotEquals(dto, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        ExamenClinicoRequest dto1 = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-01", "OK", "Dr. A", "Obs", "Res", 1L);
        ExamenClinicoRequest dto2 = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-01", "OK", "Dr. A", "Obs", "Res", 1L);
        assertEquals(dto1, dto2);
    }

    @Test
    void testEquals_diferenteNombre_retornaFalso() {
        ExamenClinicoRequest dto1 = new ExamenClinicoRequest(
                1L, "Hemograma", null, null, null, null, null, null);
        ExamenClinicoRequest dto2 = new ExamenClinicoRequest(
                1L, "TAC", null, null, null, null, null, null);
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEquals_campoNuloVsNoNulo() {
        ExamenClinicoRequest dto1 = new ExamenClinicoRequest();
        dto1.setId(1L);
        dto1.setNombre(null);
        ExamenClinicoRequest dto2 = new ExamenClinicoRequest();
        dto2.setId(1L);
        dto2.setNombre("Hemograma");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        ExamenClinicoRequest dto1 = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-01", "OK", "Dr. A", "Obs", "Res", 1L);
        ExamenClinicoRequest dto2 = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-01", "OK", "Dr. A", "Obs", "Res", 1L);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        ExamenClinicoRequest dto = new ExamenClinicoRequest();
        assertDoesNotThrow(() -> dto.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        ExamenClinicoRequest dto = new ExamenClinicoRequest();
        dto.setNombre("Hemograma");
        String str = dto.toString();
        assertNotNull(str);
        assertTrue(str.contains("ExamenClinicoRequest"));
    }
}
