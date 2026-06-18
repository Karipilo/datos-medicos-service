package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import datosmedicos_service.model.EvolucionClinica;
import datosmedicos_service.service.EvolucionClinicaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EvolucionClinicaController.class)
public class EvolucionClinicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvolucionClinicaService service;

    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void GET_evoluciones_retornaLista() throws Exception {
        EvolucionClinica e1 = new EvolucionClinica(
                LocalDateTime.now(), "Dr. Pérez", "Mejoría notable");
        e1.setPacienteId(1L);

        when(service.listarTodos(TOKEN)).thenReturn(List.of(e1));

        mockMvc.perform(get("/evoluciones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].profesional").value("Dr. Pérez"))
                .andExpect(jsonPath("$[0].descripcion").value("Mejoría notable"));
    }

    @Test
    void GET_evoluciones_listaVacia() throws Exception {
        when(service.listarTodos(TOKEN)).thenReturn(List.of());

        mockMvc.perform(get("/evoluciones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_evoluciones_creaEvolucion() throws Exception {
        EvolucionClinica evolucion = new EvolucionClinica();
        evolucion.setProfesional("Dr. Ramírez");
        evolucion.setDescripcion("Evolución favorable");
        evolucion.setPacienteId(2L);

        when(service.guardar(eq(TOKEN), any(EvolucionClinica.class))).thenReturn(evolucion);

        mockMvc.perform(post("/evoluciones")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evolucion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profesional").value("Dr. Ramírez"))
                .andExpect(jsonPath("$.descripcion").value("Evolución favorable"));

        verify(service).guardar(eq(TOKEN), any(EvolucionClinica.class));
    }

    @Test
    void GET_evolucionPorId_retornaEvolucion() throws Exception {
        EvolucionClinica evolucion = new EvolucionClinica(
                LocalDateTime.now(), "Dr. Soto", "Estable");

        when(service.buscarPorId(TOKEN, 1L)).thenReturn(evolucion);

        mockMvc.perform(get("/evoluciones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profesional").value("Dr. Soto"));
    }

    @Test
    void DELETE_evolucion_elimina() throws Exception {
        doNothing().when(service).eliminar(TOKEN, 1L);

        mockMvc.perform(delete("/evoluciones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());

        verify(service).eliminar(TOKEN, 1L);
    }

    @Test
    void PUT_evolucion_actualiza() throws Exception {
        EvolucionClinica actualizada = new EvolucionClinica();
        actualizada.setDescripcion("Nueva descripción");
        actualizada.setObservaciones("Sin cambios");

        when(service.actualizar(eq(TOKEN), eq(1L), any(EvolucionClinica.class)))
                .thenReturn(actualizada);

        mockMvc.perform(put("/evoluciones/1")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Nueva descripción"))
                .andExpect(jsonPath("$.observaciones").value("Sin cambios"));

        verify(service).actualizar(eq(TOKEN), eq(1L), any(EvolucionClinica.class));
    }
}
