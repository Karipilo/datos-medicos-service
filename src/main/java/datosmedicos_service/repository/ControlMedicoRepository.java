package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.ControlMedico;

public interface ControlMedicoRepository
        extends JpaRepository<ControlMedico, Long> {
}