package datosmedicos_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.service.FichaClinicaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FichaClinicaController.class)
public class FichaClinicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FichaClinicaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GET_fichas_retornaLista() throws Exception {
        FichaClinica f1 = new FichaClinica();
        f1.setRutPaciente("12345678-9");
        f1.setNombrePaciente("Juan Pérez");

        when(service.listar()).thenReturn(List.of(f1));

        mockMvc.perform(get("/fichas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rutPaciente").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombrePaciente").value("Juan Pérez"));
    }

    @Test
    void GET_fichas_listaVacia() throws Exception {
        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/fichas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void GET_fichasPorRut_retornaFicha() throws Exception {
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        ficha.setDiagnostico("Hipertensión");

        when(service.buscarPorRut("12345678-9")).thenReturn(ficha);

        mockMvc.perform(get("/fichas/rut/12345678-9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutPaciente").value("12345678-9"))
                .andExpect(jsonPath("$.diagnostico").value("Hipertensión"));
    }

    @Test
    void POST_fichas_creaFicha() throws Exception {
        FichaClinica ficha = new FichaClinica();
        ficha.setRutPaciente("12345678-9");
        ficha.setNombrePaciente("Juan Pérez");
        ficha.setEdad(45);

        when(service.guardar(any(FichaClinica.class))).thenReturn(ficha);

        mockMvc.perform(post("/fichas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ficha)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutPaciente").value("12345678-9"))
                .andExpect(jsonPath("$.nombrePaciente").value("Juan Pérez"));

        verify(service).guardar(any(FichaClinica.class));
    }

    @Test
    void PUT_fichas_actualizaFicha() throws Exception {
        FichaClinica fichaActualizada = new FichaClinica();
        fichaActualizada.setRutPaciente("12345678-9");
        fichaActualizada.setDiagnostico("Diabetes");
        fichaActualizada.setAlergias("Penicilina");

        when(service.actualizar(eq("12345678-9"), any(FichaClinica.class)))
                .thenReturn(fichaActualizada);

        mockMvc.perform(put("/fichas/rut/12345678-9")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fichaActualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnostico").value("Diabetes"))
                .andExpect(jsonPath("$.alergias").value("Penicilina"));

        verify(service).actualizar(eq("12345678-9"), any(FichaClinica.class));
    }
}
