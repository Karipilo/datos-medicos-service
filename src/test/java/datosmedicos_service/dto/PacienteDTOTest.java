package datosmedicos_service.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacienteDTOTest {

    @Test
    void testGettersAndSetters() {

        PacienteDTO dto = new PacienteDTO();

        dto.setId(1L);
        dto.setRut("12345678-9");
        dto.setNombre("Carlos");
        dto.setApellido("Bernal");
        dto.setDiagnostico("Diabetes");
        dto.setAlergias("Penicilina");

        assertEquals(1L, dto.getId());
        assertEquals("12345678-9", dto.getRut());
        assertEquals("Carlos", dto.getNombre());
        assertEquals("Bernal", dto.getApellido());
        assertEquals("Diabetes", dto.getDiagnostico());
        assertEquals("Penicilina", dto.getAlergias());
    }

    @Test
    void testConstructorVacio() {

        PacienteDTO dto = new PacienteDTO();

        assertNotNull(dto);
    }
}