package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import datosmedicos_service.model.Medicamento;
import datosmedicos_service.service.MedicamentoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicamentoController.class)
public class MedicamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicamentoService service;

    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void GET_medicamentos_retornaLista() throws Exception {
        Medicamento m1 = new Medicamento();
        m1.setNombre("Paracetamol");
        m1.setDosis("500mg");

        Medicamento m2 = new Medicamento();
        m2.setNombre("Ibuprofeno");
        m2.setDosis("400mg");

        when(service.listar(TOKEN)).thenReturn(List.of(m1, m2));

        mockMvc.perform(get("/medicamentos")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Paracetamol"))
                .andExpect(jsonPath("$[1].nombre").value("Ibuprofeno"));
    }

    @Test
    void GET_medicamentos_listaVacia() throws Exception {
        when(service.listar(TOKEN)).thenReturn(List.of());

        mockMvc.perform(get("/medicamentos")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_medicamento_crea() throws Exception {
        Medicamento med = new Medicamento();
        med.setNombre("Amoxicilina");
        med.setDosis("500mg");
        med.setFrecuencia("Cada 8 horas");
        med.setProfesional("Dr. Pérez");

        when(service.guardar(eq(TOKEN), any(Medicamento.class))).thenReturn(med);

        mockMvc.perform(post("/medicamentos")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(med)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Amoxicilina"))
                .andExpect(jsonPath("$.dosis").value("500mg"))
                .andExpect(jsonPath("$.frecuencia").value("Cada 8 horas"));

        verify(service).guardar(eq(TOKEN), any(Medicamento.class));
    }

    @Test
    void PUT_medicamento_actualiza() throws Exception {
        Medicamento actualizado = new Medicamento();
        actualizado.setNombre("Amoxicilina 1g");
        actualizado.setDosis("1000mg");
        actualizado.setFrecuencia("Cada 12 horas");
        actualizado.setObservaciones("Con alimentos");

        when(service.actualizar(eq(TOKEN), eq(1L), any(Medicamento.class)))
                .thenReturn(actualizado);

        mockMvc.perform(put("/medicamentos/1")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Amoxicilina 1g"))
                .andExpect(jsonPath("$.dosis").value("1000mg"))
                .andExpect(jsonPath("$.observaciones").value("Con alimentos"));

        verify(service).actualizar(eq(TOKEN), eq(1L), any(Medicamento.class));
    }
}
