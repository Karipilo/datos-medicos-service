package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.ControlMedico;
import datosmedicos_service.service.ControlMedicoService;

@RestController
@RequestMapping("/controles")
@CrossOrigin("*")
public class ControlMedicoController {

    @Autowired
    private ControlMedicoService service;

    @GetMapping
    public List<ControlMedico> listarTodos(@RequestHeader("Authorization") String token) {

        return service.listarTodos(token);
    }

    @PostMapping
    public ControlMedico guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody
            ControlMedico control
    ) {

        return service.guardar(token, control);
    }

    @GetMapping("/{id}")
    public ControlMedico buscarPorId(
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