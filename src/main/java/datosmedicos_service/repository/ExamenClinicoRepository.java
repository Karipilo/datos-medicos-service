package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.ExamenClinico;

public interface ExamenClinicoRepository
        extends JpaRepository<ExamenClinico, Long> {
}