package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import datosmedicos_service.model.SignosVitales;

public interface SignosVitalesRepository
        extends JpaRepository<SignosVitales, Long> {

    List<SignosVitales> findByFichaId(Long fichaId);

}