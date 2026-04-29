package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import datosmedicos_service.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}