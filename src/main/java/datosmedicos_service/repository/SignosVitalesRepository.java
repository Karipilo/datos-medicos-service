package datosmedicos_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datosmedicos_service.model.SignosVitales;

public interface SignosVitalesRepository
        extends JpaRepository<SignosVitales, Long> {

}