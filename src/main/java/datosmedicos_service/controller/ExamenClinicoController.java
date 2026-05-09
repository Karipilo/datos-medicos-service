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
    public List<ExamenClinico> listarTodos(@RequestHeader("Authorization") String token) {

        return service.listarTodos(token);
    }

    @PostMapping
    public ExamenClinico guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody
            ExamenClinico examen
    ) {

        return service.guardar(token, examen);
    }

    @GetMapping("/{id}")
    public ExamenClinico buscarPorId(
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