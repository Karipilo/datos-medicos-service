package datosmedicos_service.service;

import org.springframework.stereotype.Service;
import java.util.List;
import datosmedicos_service.model.Medicamento;
import datosmedicos_service.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    public List<Medicamento> listar() {
        return repository.findAll();
    }

    public Medicamento guardar(Medicamento medicamento) {
        return repository.save(medicamento);
    }
}