package datosmedicos_service.service;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import datosmedicos_service.model.EvolucionClinica;
import datosmedicos_service.repository.EvolucionClinicaRepository;

@Service
public class EvolucionClinicaService {

    @Autowired
    private EvolucionClinicaRepository repository;
    @Autowired
    private AuthService authService;

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

    public List<EvolucionClinica> listarTodos(String token) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.findAll();
    }

    public EvolucionClinica guardar(
            String token,
            EvolucionClinica evolucion) {

        System.out.println("===== EVOLUCION RECIBIDA =====");

        evolucion.setFechaRegistro(LocalDateTime.now());

        System.out.println("Fecha: " + evolucion.getFechaRegistro());

        System.out.println("Profesional: " + evolucion.getProfesional());

        System.out.println("Descripcion: " + evolucion.getDescripcion());

        System.out.println("PacienteId: " + evolucion.getPacienteId());

        return repository.save(evolucion);
    }

    public EvolucionClinica buscarPorId(
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

    public EvolucionClinica actualizar(
            String token,
            Long id,
            EvolucionClinica evolucionActualizada) {

        if (!validarToken(token)) {

            throw new RuntimeException(
                    "Token no válido -> acceso denegado");

        }

        EvolucionClinica evolucion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Evolución no encontrada"));

        evolucion.setDescripcion(
                evolucionActualizada.getDescripcion());

        evolucion.setObservaciones(
                evolucionActualizada.getObservaciones());

        return repository.save(evolucion);

    }
}