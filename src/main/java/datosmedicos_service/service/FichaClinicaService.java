package datosmedicos_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.repository.FichaClinicaRepository;

@Service
public class FichaClinicaService {

    private final FichaClinicaRepository repository;

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

    public FichaClinicaService(FichaClinicaRepository repository) {
        this.repository = repository;
    }

    public List<FichaClinica> listar() {
        return repository.findAll();
    }

    public FichaClinica guardar(FichaClinica ficha) {

        return repository.findByRutPaciente(
                ficha.getRutPaciente())
                .orElseGet(() -> repository.save(ficha));

    }

    public FichaClinica buscarPorRut(String rut) {

        return repository.findByRutPaciente(rut)
                .orElseThrow(() -> new RuntimeException(
                        "Ficha clínica no encontrada"));

    }

    public FichaClinica actualizar(
            String rut,
            FichaClinica datos) {

        FichaClinica ficha = repository.findByRutPaciente(rut)
                .orElseThrow(() -> new RuntimeException(
                        "Ficha no encontrada"));

        ficha.setDiagnostico(
                datos.getDiagnostico());

        ficha.setAlergias(
                datos.getAlergias());

        ficha.setObservaciones(
                datos.getObservaciones());

        ficha.setEdad(
                datos.getEdad());

        ficha.setGenero(
                datos.getGenero());

        return repository.save(ficha);
    }
}