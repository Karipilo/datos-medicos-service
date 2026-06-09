package datosmedicos_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import datosmedicos_service.model.Antropometria;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.AntropometriaRepository;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class AntropometriaService {

        private final AntropometriaRepository repository;
        private final FichaClinicaRepository fichaRepository;

        public AntropometriaService(
                        AntropometriaRepository repository,
                        FichaClinicaRepository fichaRepository) {

                this.repository = repository;
                this.fichaRepository = fichaRepository;
        }

        public Antropometria guardar(
                        Long fichaId,
                        Antropometria antropometria) {

                FichaClinica ficha = fichaRepository.findById(fichaId)
                                .orElseThrow(() -> new RuntimeException(
                                                "Ficha no encontrada"));

                System.out.println(
                                "FICHA ENCONTRADA: "
                                                + ficha.getId());

                antropometria.setFicha(ficha);

                System.out.println(
                                "FICHA ASIGNADA A ANTROPOMETRIA: "
                                                + antropometria.getFicha().getId());

                antropometria.setFechaRegistro(
                                LocalDateTime.now());

                Antropometria guardada = repository.save(antropometria);

                System.out.println(
                                "FICHA GUARDADA EN BD: "
                                                + (guardada.getFicha() != null
                                                                ? guardada.getFicha().getId()
                                                                : "NULL"));

                return guardada;
        }

        public List<Antropometria> listarPorFicha(
                        Long fichaId) {

                return repository.findByFichaId(
                                fichaId);
        }
}