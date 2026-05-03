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
    public List<ControlMedico> listarTodos() {

        return service.listarTodos();
    }

    @PostMapping
    public ControlMedico guardar(
            @RequestBody
            ControlMedico control
    ) {

        return service.guardar(control);
    }

    @GetMapping("/{id}")
    public ControlMedico buscarPorId(
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