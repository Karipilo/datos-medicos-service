package datosmedicos_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import datosmedicos_service.dto.ExamenClinicoRequest;
import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.ExamenClinicoRepository;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class ExamenClinicoService {

    @Autowired
    private ExamenClinicoRepository repository;
    @Autowired
    private AuthService authService;

    @Autowired
    private FichaClinicaRepository fichaRepository;

    @Value("${auth.url}")
    private String AUTH_URL;

    public boolean validarToken(String token) {
        token = token.replaceAll("Bearer ", ""); // Eliminar el prefijo "Bearer " si está

        try {

            ResponseEntity<String> response = authService.get(AUTH_URL, token);

            return response.getStatusCode() == HttpStatus.OK;

        } catch (HttpClientErrorException e) {

            System.out.println("Token no válido -> acceso denegado");
            return false;

        } catch (Exception e) {

            System.out.println("Error validando token: " + e.getMessage());
            return false;
        }
    }

    public List<ExamenClinicoRequest> listarTodos(String token) {

        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido");
        }

        return repository.findAll()
                .stream()
                .map(e -> new ExamenClinicoRequest(
                        e.getId(),
                        e.getNombre(),
                        e.getFechaRegistro() != null
                                ? e.getFechaRegistro().toString()
                                : null,
                        e.getEstado(),
                        e.getProfesional(),
                        e.getObservacion(),
                        e.getResultado(),
                        e.getFicha() != null ? e.getFicha().getId() : null))
                .toList();
    }

    public ExamenClinico guardar(
            String token,
            ExamenClinicoRequest request) {

        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido");
        }

        FichaClinica ficha = fichaRepository.findById(request.getFicha())
                .orElseThrow(() -> new RuntimeException("Ficha no encontrada"));

        ExamenClinico examen = new ExamenClinico();

        examen.setNombre(request.getNombre());
        examen.setFechaRegistro(LocalDateTime.now());
        examen.setEstado(request.getEstado());
        examen.setProfesional(request.getProfesional());
        examen.setObservacion(request.getObservacion());
        examen.setResultado(request.getResultado());

        // IMPORTANTE
        examen.setFicha(ficha);

        return repository.save(examen);
    }

    public ExamenClinico buscarPorId(
            String token,
            Long id) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(String token, Long id) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        repository.deleteById(id);
    }

    public ExamenClinico actualizar(
            String token,
            Long id,
            ExamenClinicoRequest examenActualizado) {

        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }

        ExamenClinico examen = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examen no encontrado"));

        examen.setEstado(examenActualizado.getEstado());
        examen.setResultado(examenActualizado.getResultado());
        examen.setObservacion(examenActualizado.getObservacion());

        return repository.save(examen);
    }
}