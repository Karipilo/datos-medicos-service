package datosmedicos_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.Antropometria;

public interface AntropometriaRepository
        extends JpaRepository<Antropometria, Long> {

    List<Antropometria> findByFichaId(Long fichaId);

}