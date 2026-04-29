package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import datosmedicos_service.model.FichaClinica;

public interface FichaClinicaRepository extends JpaRepository<FichaClinica, Long> {
}