package datosmedicos_service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.service.ControlMedicoService;

@RestController
@RequestMapping("/controles")
public class ControlMedicoController {

    private final ControlMedicoService service;

    public ControlMedicoController(ControlMedicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ControlMedico> listar() {
        return service.listar();
    }

    @PostMapping("/{fichaId}")
public ControlMedico guardar(@PathVariable Long fichaId, @RequestBody ControlMedico control) {
    return service.guardarConFicha(fichaId, control);
}
}