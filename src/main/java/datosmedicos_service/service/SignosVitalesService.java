package datosmedicos_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.repository.SignosVitalesRepository;

@Service
public class SignosVitalesService {

    @Autowired
    private SignosVitalesRepository repository;
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
    public List<SignosVitales> listarTodos(String token) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.findAll();
    }

    public SignosVitales guardar(
            String token,
            SignosVitales signosVitales
    ) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.save(signosVitales);
    }
    public SignosVitales guardar(
            SignosVitales signosVitales
    ) {
        return repository.save(signosVitales);
    }

    public SignosVitales buscarPorId(
            String token,
            Long id
    ) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        return repository.findById(id)
                .orElse(null);
    }

    public void eliminar(
            String token,
            Long id
    ) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token no válido -> acceso denegado");
        }
        repository.deleteById(id);
    }
}