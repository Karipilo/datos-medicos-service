package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.repository.ExamenClinicoRepository;

@Service
public class ExamenClinicoService {

    @Autowired
    private ExamenClinicoRepository repository;

    public List<ExamenClinico> listarTodos() {

        return repository.findAll();
    }

    public ExamenClinico guardar(
            ExamenClinico examen
    ) {

        return repository.save(examen);
    }

    public ExamenClinico buscarPorId(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}