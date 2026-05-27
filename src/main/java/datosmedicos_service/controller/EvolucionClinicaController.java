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
    public List<EvolucionClinica> listarTodos(@RequestHeader("Authorization") String token) {
        return service.listarTodos(token);
    }

    @PostMapping
    public EvolucionClinica guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody
            EvolucionClinica evolucion
    ) {

        return service.guardar(token, evolucion);
    }

    @GetMapping("/{id}")
    public EvolucionClinica buscarPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {

        return service.buscarPorId(token, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {

        service.eliminar(token, id);
    }
}