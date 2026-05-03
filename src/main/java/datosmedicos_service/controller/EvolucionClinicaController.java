package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.EvolucionClinica;
import datosmedicos_service.service.EvolucionClinicaService;

@RestController
@RequestMapping("/evoluciones")
@CrossOrigin("*")
public class EvolucionClinicaController {

    @Autowired
    private EvolucionClinicaService service;

    @GetMapping
    public List<EvolucionClinica> listarTodos() {

        return service.listarTodos();
    }

    @PostMapping
    public EvolucionClinica guardar(
            @RequestBody
            EvolucionClinica evolucion
    ) {

        return service.guardar(evolucion);
    }

    @GetMapping("/{id}")
    public EvolucionClinica buscarPorId(
            @PathVariable Long id
    ) {

        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id
    ) {

        service.eliminar(id);
    }
}