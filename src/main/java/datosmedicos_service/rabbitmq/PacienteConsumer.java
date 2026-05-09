package datosmedicos_service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PacienteConsumer {

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE
    )
    public void recibirPaciente(
            PacienteCreadoEvent event
    ) {

        System.out.println(
                "Paciente recibido: "
                + event.getNombre()
        );
    }
}