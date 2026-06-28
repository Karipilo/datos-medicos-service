package datosmedicos_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import datosmedicos_service.dto.PacienteDTO;

@Service
public class PacienteClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL_PACIENTES =
            "http://alb-ms-pacientes-1492385033.us-east-1.elb.amazonaws.com:8082/pacientes";

    public PacienteDTO obtenerPaciente(Long id) {

        return restTemplate.getForObject(
                URL_PACIENTES + "/" + id,
                PacienteDTO.class
        );
    }
}