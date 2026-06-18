package datosmedicos_service.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FichaClinicaTest {

    @Test
    void testGettersSetters() {

        FichaClinica ficha = new FichaClinica();

        ficha.setId(1L);
        ficha.setNombrePaciente("Carlos");
        ficha.setRutPaciente("11111111-1");
        ficha.setEdad(30);

        assertEquals(1L, ficha.getId());
        assertEquals("Carlos", ficha.getNombrePaciente());
        assertEquals("11111111-1", ficha.getRutPaciente());
        assertEquals(30, ficha.getEdad());
    }

    @Test
    void testGettersSetters_camposAdicionales() {
        FichaClinica ficha = new FichaClinica();

        ficha.setDiagnostico("Hipertension");
        ficha.setAlergias("Penicilina");
        ficha.setObservaciones("Control mensual");
        ficha.setGenero("Femenino");

        assertEquals("Hipertension", ficha.getDiagnostico());
        assertEquals("Penicilina", ficha.getAlergias());
        assertEquals("Control mensual", ficha.getObservaciones());
        assertEquals("Femenino", ficha.getGenero());
    }

    @Test
    void testSetListas() {
        FichaClinica ficha = new FichaClinica();
        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento());
        List<Antropometria> antropometrias = new ArrayList<>();
        antropometrias.add(new Antropometria());
        List<ExamenClinico> examenes = new ArrayList<>();
        examenes.add(new ExamenClinico());

        ficha.setMedicamentos(medicamentos);
        ficha.setAntropometrias(antropometrias);
        ficha.setExamenes(examenes);

        assertEquals(1, ficha.getMedicamentos().size());
        assertEquals(1, ficha.getAntropometrias().size());
        assertEquals(1, ficha.getExamenes().size());
    }

    @Test
    void testAllArgsConstructor() {
        List<Medicamento> meds = new ArrayList<>();
        List<Antropometria> antros = new ArrayList<>();
        List<ExamenClinico> exams = new ArrayList<>();

        FichaClinica ficha = new FichaClinica(
                1L, "Ana", "22222222-2", 25,
                "Diabetes", "Aspirina", "Sin obs", "Femenino",
                meds, antros, exams);

        assertEquals(1L, ficha.getId());
        assertEquals("Ana", ficha.getNombrePaciente());
        assertEquals("22222222-2", ficha.getRutPaciente());
        assertEquals(25, ficha.getEdad());
        assertEquals("Diabetes", ficha.getDiagnostico());
        assertEquals("Femenino", ficha.getGenero());
    }

    @Test
    void testEquals_mismoObjeto() {
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("11111111-1");
        assertEquals(ficha, ficha);
    }

    @Test
    void testEquals_nulo_retornaFalso() {
        FichaClinica ficha = new FichaClinica();
        assertNotEquals(ficha, null);
    }

    @Test
    void testEquals_diferenteTipo_retornaFalso() {
        FichaClinica ficha = new FichaClinica();
        assertNotEquals(ficha, "cadena");
    }

    @Test
    void testEquals_objetosIguales_retornaVerdadero() {
        FichaClinica f1 = new FichaClinica();
        f1.setId(1L);
        f1.setRutPaciente("11111111-1");
        FichaClinica f2 = new FichaClinica();
        f2.setId(1L);
        f2.setRutPaciente("11111111-1");
        assertEquals(f1, f2);
    }

    @Test
    void testEquals_diferenteRut_retornaFalso() {
        FichaClinica f1 = new FichaClinica();
        f1.setRutPaciente("11111111-1");
        FichaClinica f2 = new FichaClinica();
        f2.setRutPaciente("22222222-2");
        assertNotEquals(f1, f2);
    }

    @Test
    void testEquals_campoNuloVsNoNulo() {
        FichaClinica f1 = new FichaClinica();
        f1.setId(1L);
        f1.setRutPaciente(null);
        FichaClinica f2 = new FichaClinica();
        f2.setId(1L);
        f2.setRutPaciente("11111111-1");
        assertNotEquals(f1, f2);
    }

    @Test
    void testHashCode_mismoParaObjetosIguales() {
        FichaClinica f1 = new FichaClinica();
        f1.setId(1L);
        f1.setRutPaciente("11111111-1");
        FichaClinica f2 = new FichaClinica();
        f2.setId(1L);
        f2.setRutPaciente("11111111-1");
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void testHashCode_noLanzaExcepcion() {
        FichaClinica ficha = new FichaClinica();
        assertDoesNotThrow(() -> ficha.hashCode());
    }

    @Test
    void testToString_contieneNombreClase() {
        FichaClinica ficha = new FichaClinica();
        ficha.setNombrePaciente("Carlos");
        String str = ficha.toString();
        assertNotNull(str);
        assertTrue(str.contains("FichaClinica"));
    }
}
