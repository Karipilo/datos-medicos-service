package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.EvolucionClinica;

public interface EvolucionClinicaRepository
        extends JpaRepository<EvolucionClinica, Long> {
}