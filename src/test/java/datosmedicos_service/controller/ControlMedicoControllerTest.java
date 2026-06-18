package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.service.ControlMedicoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ControlMedicoController.class)
public class ControlMedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ControlMedicoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @Test
    void GET_controles_retornaLista() throws Exception {
        ControlMedico c1 = new ControlMedico("2024-01-10", "General", "Dr. Pérez", "Sin novedades");
        ControlMedico c2 = new ControlMedico("2024-02-15", "Cardiología", "Dr. Gómez", "Estable");

        when(service.listarTodos(TOKEN)).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/controles")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].profesional").value("Dr. Pérez"))
                .andExpect(jsonPath("$[1].tipoControl").value("Cardiología"));
    }

    @Test
    void GET_controles_listaVacia() throws Exception {
        when(service.listarTodos(TOKEN)).thenReturn(List.of());

        mockMvc.perform(get("/controles")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_controles_creaControl() throws Exception {
        ControlMedico control = new ControlMedico(
                "2024-03-20", "Neurología", "Dr. Ramírez", "Control de rutina");

        when(service.guardar(eq(TOKEN), any(ControlMedico.class))).thenReturn(control);

        mockMvc.perform(post("/controles")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(control)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoControl").value("Neurología"))
                .andExpect(jsonPath("$.profesional").value("Dr. Ramírez"));

        verify(service).guardar(eq(TOKEN), any(ControlMedico.class));
    }

    @Test
    void GET_controlPorId_retornaControl() throws Exception {
        ControlMedico control = new ControlMedico(
                "2024-01-10", "General", "Dr. Soto", "Observación estable");

        when(service.buscarPorId(TOKEN, 1L)).thenReturn(control);

        mockMvc.perform(get("/controles/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profesional").value("Dr. Soto"));
    }

    @Test
    void GET_controlPorId_noExiste_retornaNull() throws Exception {
        when(service.buscarPorId(TOKEN, 99L)).thenReturn(null);

        mockMvc.perform(get("/controles/99")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    void DELETE_control_elimina() throws Exception {
        doNothing().when(service).eliminar(TOKEN, 1L);

        mockMvc.perform(delete("/controles/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());

        verify(service).eliminar(TOKEN, 1L);
    }
}
