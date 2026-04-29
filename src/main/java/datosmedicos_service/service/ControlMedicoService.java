package datosmedicos_service.service;

import org.springframework.stereotype.Service;
import java.util.List;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.ControlMedicoRepository;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class ControlMedicoService {

    private final ControlMedicoRepository repository;
    private final FichaClinicaRepository fichaRepository;

    public ControlMedicoService(ControlMedicoRepository repository, FichaClinicaRepository fichaRepository) {
        this.repository = repository;
        this.fichaRepository = fichaRepository;
    }

    public List<ControlMedico> listar() {
        return repository.findAll();
    }

    public ControlMedico guardarConFicha(Long fichaId, ControlMedico control) {
        FichaClinica ficha = fichaRepository.findById(fichaId)
                .orElseThrow(() -> new RuntimeException("Ficha no encontrada"));

        control.setFicha(ficha);
        return repository.save(control);
    }
}