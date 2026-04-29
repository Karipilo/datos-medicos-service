package datosmedicos_service.service;

import org.springframework.stereotype.Service;
import java.util.List;

import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class FichaClinicaService {

    private final FichaClinicaRepository repository;

    public FichaClinicaService(FichaClinicaRepository repository) {
        this.repository = repository;
    }

    public List<FichaClinica> listar() {
        return repository.findAll();
    }

    public FichaClinica guardar(FichaClinica ficha) {
        return repository.save(ficha);
    }
}