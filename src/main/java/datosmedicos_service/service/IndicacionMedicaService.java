package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import datosmedicos_service.model.IndicacionMedica;
import datosmedicos_service.repository.IndicacionMedicaRepository;

@Service
public class IndicacionMedicaService {

    @Autowired
    private IndicacionMedicaRepository repository;

    @Autowired
    private AuthService authService;

    @Value("${auth.url}")
    private String AUTH_URL;

    public boolean validarToken(String token) {

        try {

            ResponseEntity<String> response =
                    authService.get(AUTH_URL, token);

            return response.getStatusCode() == HttpStatus.OK;

        } catch (HttpClientErrorException e) {

            System.out.println("Token no válido -> acceso denegado");
            return false;

        } catch (Exception e) {

            System.out.println("Error validando token: " + e.getMessage());
            return false;
        }
    }
    public List<IndicacionMedica> listarTodos(String token) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.findAll();
    }

    public IndicacionMedica guardar(
            String token,
            IndicacionMedica indicacion
    ) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.save(indicacion);
    }

    public IndicacionMedica buscarPorId(
            String token,
            Long id
    ) {
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
}