package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import datosmedicos_service.dto.ExamenClinicoRequest;
import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.service.ExamenClinicoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExamenClinicoController.class)
public class ExamenClinicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamenClinicoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @Test
    void GET_examenes_retornaLista() throws Exception {
        ExamenClinicoRequest e1 = new ExamenClinicoRequest(
                1L, "Hemograma", "2024-01-10", "Pendiente", "Dr. Pérez", null, null, 1L);
        ExamenClinicoRequest e2 = new ExamenClinicoRequest(
                2L, "Radiografía", "2024-01-11", "Completado", "Dr. Gómez", null, "Normal", 1L);

        when(service.listarTodos(TOKEN)).thenReturn(List.of(e1, e2));

        mockMvc.perform(get("/examenes")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Hemograma"))
                .andExpect(jsonPath("$[1].estado").value("Completado"));
    }

    @Test
    void GET_examenes_listaVacia() throws Exception {
        when(service.listarTodos(TOKEN)).thenReturn(List.of());

        mockMvc.perform(get("/examenes")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_examen_crea() throws Exception {
        ExamenClinicoRequest request = new ExamenClinicoRequest(
                null, "Eco abdominal", null, "Pendiente", "Dr. Soto",
                "Sin observaciones", null, 1L);

        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("Eco abdominal");
        examen.setEstado("Pendiente");

        when(service.guardar(eq(TOKEN), any(ExamenClinicoRequest.class))).thenReturn(examen);

        mockMvc.perform(post("/examenes")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Eco abdominal"))
                .andExpect(jsonPath("$.estado").value("Pendiente"));

        verify(service).guardar(eq(TOKEN), any(ExamenClinicoRequest.class));
    }

    @Test
    void GET_examenPorId_retornaExamen() throws Exception {
        ExamenClinico examen = new ExamenClinico();
        examen.setNombre("TAC");
        examen.setEstado("Completado");
        examen.setResultado("Sin hallazgos");

        when(service.buscarPorId(TOKEN, 1L)).thenReturn(examen);

        mockMvc.perform(get("/examenes/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("TAC"))
                .andExpect(jsonPath("$.estado").value("Completado"));
    }

    @Test
    void DELETE_examen_elimina() throws Exception {
        doNothing().when(service).eliminar(TOKEN, 1L);

        mockMvc.perform(delete("/examenes/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());

        verify(service).eliminar(TOKEN, 1L);
    }

    @Test
    void PUT_examen_actualiza() throws Exception {
        ExamenClinicoRequest request = new ExamenClinicoRequest(
                1L, "Hemograma", null, "Completado", "Dr. Pérez",
                "Valores normales", "Todo en rango", 1L);

        ExamenClinico actualizado = new ExamenClinico();
        actualizado.setEstado("Completado");
        actualizado.setResultado("Todo en rango");

        when(service.actualizar(eq(TOKEN), eq(1L), any(ExamenClinicoRequest.class)))
                .thenReturn(actualizado);

        mockMvc.perform(put("/examenes/1")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Completado"))
                .andExpect(jsonPath("$.resultado").value("Todo en rango"));

        verify(service).actualizar(eq(TOKEN), eq(1L), any(ExamenClinicoRequest.class));
    }
}
