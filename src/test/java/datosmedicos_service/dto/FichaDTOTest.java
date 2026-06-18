package datosmedicos_service.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FichaDTOTest {

    @Test
    void testGettersAndSetters() {

        FichaDTO dto = new FichaDTO();

        dto.setId(10L);

        assertEquals(10L, dto.getId());
    }

    @Test
    void testConstructorAllArgs() {

        FichaDTO dto = new FichaDTO(20L);

        assertEquals(20L, dto.getId());
    }

    @Test
    void testConstructorNoArgs() {

        FichaDTO dto = new FichaDTO();

        assertNotNull(dto);
    }

    @Test
    void testEquals_mismoObjeto() {
        FichaDTO dto = new FichaDTO(5L);
        assertEquals(dto, dto);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        FichaDTO dto = new FichaDTO(5L);
        assertNotEquals(dto, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        FichaDTO dto = new FichaDTO(5L);
        assertNotEquals(dto, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        FichaDTO dto1 = new FichaDTO(10L);
        FichaDTO dto2 = new FichaDTO(10L);
        assertEquals(dto1, dto2);
    }

    @Test
    void testEquals_diferenteId_retornaFalso() {
        FichaDTO dto1 = new FichaDTO(10L);
        FichaDTO dto2 = new FichaDTO(20L);
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEquals_idNuloVsNoNulo() {
        FichaDTO dto1 = new FichaDTO(null);
        FichaDTO dto2 = new FichaDTO(10L);
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        FichaDTO dto1 = new FichaDTO(10L);
        FichaDTO dto2 = new FichaDTO(10L);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        FichaDTO dto = new FichaDTO();
        assertDoesNotThrow(() -> dto.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        FichaDTO dto = new FichaDTO(15L);
        String str = dto.toString();
        assertNotNull(str);
        assertTrue(str.contains("FichaDTO"));
    }
}
