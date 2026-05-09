package datosmedicos_service.service;

import org.springframework.stereotype.Service;
import java.util.List;

import datosmedicos_service.client.PacienteClient;
import datosmedicos_service.dto.PacienteDTO;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class FichaClinicaService {

    private final FichaClinicaRepository repository;
    private final PacienteClient pacienteClient;

    public FichaClinicaService(
            FichaClinicaRepository repository,
            PacienteClient pacienteClient) {

        this.repository = repository;
        this.pacienteClient = pacienteClient;
    }

    public List<FichaClinica> listar() {
        return repository.findAll();
    }

    public FichaClinica guardar(FichaClinica ficha) {
        return repository.save(ficha);
    }

    // OBTENER DATOS DEL PACIENTE DESDE MICROSERVICIO PACIENTES

    public PacienteDTO obtenerPaciente(Long id) {

        return pacienteClient.obtenerPaciente(id);
    }
}