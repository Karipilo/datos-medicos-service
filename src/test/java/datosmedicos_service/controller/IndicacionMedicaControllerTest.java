package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import datosmedicos_service.model.IndicacionMedica;
import datosmedicos_service.service.IndicacionMedicaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(IndicacionMedicaController.class)
public class IndicacionMedicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndicacionMedicaService service;

    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void GET_indicaciones_retornaLista() throws Exception {
        IndicacionMedica i1 = new IndicacionMedica(LocalDateTime.now(), "Dr. A", "Reposo");
        IndicacionMedica i2 = new IndicacionMedica(LocalDateTime.now(), "Dr. B", "Dieta blanda");

        when(service.listarTodos(TOKEN)).thenReturn(List.of(i1, i2));

        mockMvc.perform(get("/indicaciones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].indicacion").value("Reposo"))
                .andExpect(jsonPath("$[1].indicacion").value("Dieta blanda"));
    }

    @Test
    void GET_indicaciones_listaVacia() throws Exception {
        when(service.listarTodos(TOKEN)).thenReturn(List.of());

        mockMvc.perform(get("/indicaciones")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_indicacion_crea() throws Exception {
        IndicacionMedica indicacion = new IndicacionMedica();
        indicacion.setProfesional("Dr. Soto");
        indicacion.setIndicacion("Reposo absoluto 5 días");

        when(service.guardar(eq(TOKEN), any(IndicacionMedica.class))).thenReturn(indicacion);

        mockMvc.perform(post("/indicaciones")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(indicacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profesional").value("Dr. Soto"))
                .andExpect(jsonPath("$.indicacion").value("Reposo absoluto 5 días"));

        verify(service).guardar(eq(TOKEN), any(IndicacionMedica.class));
    }

    @Test
    void GET_indicacionPorId_retornaIndicacion() throws Exception {
        IndicacionMedica indicacion = new IndicacionMedica(
                LocalDateTime.now(), "Dr. López", "Dieta hiposódica");

        when(service.buscarPorId(TOKEN, 1L)).thenReturn(indicacion);

        mockMvc.perform(get("/indicaciones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profesional").value("Dr. López"))
                .andExpect(jsonPath("$.indicacion").value("Dieta hiposódica"));
    }

    @Test
    void GET_indicacionPorId_noExiste_retornaNull() throws Exception {
        when(service.buscarPorId(TOKEN, 99L)).thenReturn(null);

        mockMvc.perform(get("/indicaciones/99")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    void DELETE_indicacion_elimina() throws Exception {
        doNothing().when(service).eliminar(TOKEN, 1L);

        mockMvc.perform(delete("/indicaciones/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());

        verify(service).eliminar(TOKEN, 1L);
    }

    @Test
    void PUT_indicacion_actualiza() throws Exception {
        IndicacionMedica actualizada = new IndicacionMedica();
        actualizada.setIndicacion("Reposo absoluto 10 días");

        when(service.actualizar(eq(TOKEN), eq(1L), any(IndicacionMedica.class)))
                .thenReturn(actualizada);

        mockMvc.perform(put("/indicaciones/1")
                .header("Authorization", TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.indicacion").value("Reposo absoluto 10 días"));

        verify(service).actualizar(eq(TOKEN), eq(1L), any(IndicacionMedica.class));
    }
}
