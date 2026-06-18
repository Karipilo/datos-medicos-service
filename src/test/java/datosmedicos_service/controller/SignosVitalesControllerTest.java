package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.service.SignosVitalesService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SignosVitalesController.class)
public class SignosVitalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignosVitalesService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOKEN = "Bearer token-valido";

    @Test
    void GET_signosVitales_retornaLista() throws Exception {
        SignosVitales s1 = new SignosVitales();
        s1.setPresion("120/80");
        s1.setFrecuencia(72);

        when(service.listarTodos(TOKEN)).thenReturn(List.of(s1));

        mockMvc.perform(get("/signos-vitales")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].presion").value("120/80"))
                .andExpect(jsonPath("$[0].frecuencia").value(72));
    }

    @Test
    void POST_signosVitales_conFichaId_guarda() throws Exception {
        SignosVitales signos = new SignosVitales();
        signos.setPresion("130/85");
        signos.setTemperatura(37.0);

        when(service.guardar(eq(1L), any(SignosVitales.class))).thenReturn(signos);

        mockMvc.perform(post("/signos-vitales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.presion").value("130/85"));

        verify(service).guardar(eq(1L), any(SignosVitales.class));
    }

    @Test
    void GET_signosVitalesPorFicha_retornaLista() throws Exception {
        SignosVitales s1 = new SignosVitales();
        s1.setPresion("110/70");
        SignosVitales s2 = new SignosVitales();
        s2.setPresion("120/80");

        when(service.listarPorFicha(1L)).thenReturn(List.of(s1, s2));

        mockMvc.perform(get("/signos-vitales/ficha/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].presion").value("110/70"));
    }

    @Test
    void GET_signosVitalesPorId_retornaSigno() throws Exception {
        SignosVitales signos = new SignosVitales();
        signos.setPresion("120/80");
        signos.setSaturacion(98);

        when(service.buscarPorId(TOKEN, 1L)).thenReturn(signos);

        mockMvc.perform(get("/signos-vitales/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.presion").value("120/80"))
                .andExpect(jsonPath("$.saturacion").value(98));
    }

    @Test
    void DELETE_signosVitales_elimina() throws Exception {
        doNothing().when(service).eliminar(TOKEN, 1L);

        mockMvc.perform(delete("/signos-vitales/1")
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());

        verify(service).eliminar(TOKEN, 1L);
    }

    @Test
    void GET_signosVitalesPorFicha_listaVacia() throws Exception {
        when(service.listarPorFicha(99L)).thenReturn(List.of());

        mockMvc.perform(get("/signos-vitales/ficha/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
