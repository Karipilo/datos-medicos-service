package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import datosmedicos_service.model.Antropometria;
import datosmedicos_service.service.AntropometriaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AntropometriaController.class)
public class AntropometriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AntropometriaService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void POST_antropometria_guarda() throws Exception {
        Antropometria antro = new Antropometria();
        antro.setPeso(70.5);
        antro.setAltura(1.75);
        antro.setProfesional("Dr. López");

        when(service.guardar(eq(1L), any(Antropometria.class))).thenReturn(antro);

        mockMvc.perform(post("/antropometrias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(antro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peso").value(70.5))
                .andExpect(jsonPath("$.altura").value(1.75))
                .andExpect(jsonPath("$.profesional").value("Dr. López"));

        verify(service).guardar(eq(1L), any(Antropometria.class));
    }

    @Test
    void GET_antropometriasPorFicha_retornaLista() throws Exception {
        Antropometria a1 = new Antropometria();
        a1.setPeso(68.0);
        a1.setAltura(1.70);

        Antropometria a2 = new Antropometria();
        a2.setPeso(70.0);
        a2.setAltura(1.70);

        when(service.listarPorFicha(1L)).thenReturn(List.of(a1, a2));

        mockMvc.perform(get("/antropometrias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].peso").value(68.0))
                .andExpect(jsonPath("$[1].peso").value(70.0));
    }

    @Test
    void GET_antropometriasPorFicha_listaVacia() throws Exception {
        when(service.listarPorFicha(99L)).thenReturn(List.of());

        mockMvc.perform(get("/antropometrias/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void POST_antropometria_datosCompletos() throws Exception {
        Antropometria antro = new Antropometria();
        antro.setPeso(85.0);
        antro.setAltura(1.80);
        antro.setProfesional("Dra. Martínez");

        when(service.guardar(eq(2L), any(Antropometria.class))).thenReturn(antro);

        mockMvc.perform(post("/antropometrias/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(antro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peso").value(85.0))
                .andExpect(jsonPath("$.profesional").value("Dra. Martínez"));
    }
}
