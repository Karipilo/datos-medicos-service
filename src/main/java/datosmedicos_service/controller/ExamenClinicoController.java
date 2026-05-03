package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.service.ExamenClinicoService;

@RestController
@RequestMapping("/examenes")
@CrossOrigin("*")
public class ExamenClinicoController {

    @Autowired
    private ExamenClinicoService service;

    @GetMapping
    public List<ExamenClinico> listarTodos() {

        return service.listarTodos();
    }

    @PostMapping
    public ExamenClinico guardar(
            @RequestBody
            ExamenClinico examen
    ) {

        return service.guardar(examen);
    }

    @GetMapping("/{id}")
    public ExamenClinico buscarPorId(
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