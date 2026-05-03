package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datosmedicos_service.model.IndicacionMedica;
import datosmedicos_service.repository.IndicacionMedicaRepository;

@Service
public class IndicacionMedicaService {

    @Autowired
    private IndicacionMedicaRepository repository;

    public List<IndicacionMedica> listarTodos() {

        return repository.findAll();
    }

    public IndicacionMedica guardar(
            IndicacionMedica indicacion
    ) {

        return repository.save(indicacion);
    }

    public IndicacionMedica buscarPorId(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}