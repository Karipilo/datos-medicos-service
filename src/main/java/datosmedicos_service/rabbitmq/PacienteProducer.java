package datosmedicos_service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarPaciente(
            PacienteCreadoEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.QUEUE,
                event
        );

        System.out.println(
                "Paciente enviado a RabbitMQ"
        );
    }
}