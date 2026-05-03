package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datosmedicos_service.model.EvolucionClinica;
import datosmedicos_service.repository.EvolucionClinicaRepository;

@Service
public class EvolucionClinicaService {

    @Autowired
    private EvolucionClinicaRepository repository;

    public List<EvolucionClinica> listarTodos() {

        return repository.findAll();
    }

    public EvolucionClinica guardar(
            EvolucionClinica evolucion
    ) {

        return repository.save(evolucion);
    }

    public EvolucionClinica buscarPorId(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}