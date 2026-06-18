package datosmedicos_service.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import datosmedicos_service.rabbitmq.PacienteProducer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RabbitTestController.class)
public class RabbitTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteProducer producer;

    @Test
    void POST_rabbit_enviarMensaje_retornaMensaje() throws Exception {
        doNothing().when(producer).enviarPaciente(any());

        mockMvc.perform(post("/rabbit"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mensaje enviado"));

        verify(producer).enviarPaciente(any());
    }
}
