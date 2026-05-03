package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.repository.ControlMedicoRepository;

@Service
public class ControlMedicoService {

    @Autowired
    private ControlMedicoRepository repository;

    public List<ControlMedico> listarTodos() {

        return repository.findAll();
    }

    public ControlMedico guardar(
            ControlMedico control
    ) {

        return repository.save(control);
    }

    public ControlMedico buscarPorId(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}