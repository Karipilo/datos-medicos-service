package datosmedicos_service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import datosmedicos_service.model.Medicamento;
import datosmedicos_service.service.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    public MedicamentoController(MedicamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medicamento> listar() {
        return service.listar();
    }

    @PostMapping
    public Medicamento guardar(@RequestBody Medicamento medicamento) {
        return service.guardar(medicamento);
    }
}