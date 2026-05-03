package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.repository.SignosVitalesRepository;

@Service
public class SignosVitalesService {

    @Autowired
    private SignosVitalesRepository repository;

    public List<SignosVitales> listarTodos() {

        return repository.findAll();
    }

    public SignosVitales guardar(
            SignosVitales signosVitales
    ) {

        return repository.save(
                signosVitales
        );
    }

    public SignosVitales buscarPorId(
            Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(
            Long id
    ) {

        repository.deleteById(id);
    }
}