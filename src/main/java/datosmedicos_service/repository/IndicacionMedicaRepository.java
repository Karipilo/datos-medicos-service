package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.IndicacionMedica;

public interface IndicacionMedicaRepository
        extends JpaRepository<IndicacionMedica, Long> {
}