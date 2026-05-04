package datosmedicos_service.controller;

import datosmedicos_service.rabbitmq.PacienteCreadoEvent;
import datosmedicos_service.rabbitmq.PacienteProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitTestController {

    @Autowired
    private PacienteProducer producer;

    @PostMapping
    public String enviarMensaje() {

        PacienteCreadoEvent event =
                new PacienteCreadoEvent();

        event.setId(1L);

        event.setNombre("Juan Pérez");

        event.setRut("11.111.111-1");

        producer.enviarPaciente(event);

        return "Mensaje enviado";
    }
}