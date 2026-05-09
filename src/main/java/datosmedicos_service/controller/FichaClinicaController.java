package datosmedicos_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import datosmedicos_service.dto.PacienteDTO;
import datosmedicos_service.model.FichaClinica;
import datosmedicos_service.service.FichaClinicaService;

@RestController
@RequestMapping("/fichas")
public class FichaClinicaController {

    private final FichaClinicaService service;

    public FichaClinicaController(FichaClinicaService service) {
        this.service = service;
    }

    // GET → listar fichas

    @GetMapping
    public List<FichaClinica> listar() {
        return service.listar();
    }

    // POST → guardar ficha

    @PostMapping
    public FichaClinica guardar(@RequestBody FichaClinica ficha) {
        return service.guardar(ficha);
    }

    // GET → obtener paciente desde microservicio Pacientes

    @GetMapping("/paciente/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.obtenerPaciente(id)
        );
    }
}