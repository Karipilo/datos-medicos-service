package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.dto.ExamenClinicoRequest;
import datosmedicos_service.model.ExamenClinico;
import datosmedicos_service.service.ExamenClinicoService;

@RestController
@RequestMapping("/examenes")
@CrossOrigin("*")
public class ExamenClinicoController {

    @Autowired
    private ExamenClinicoService service;

    @GetMapping
    public List<ExamenClinicoRequest> listarTodos(@RequestHeader("Authorization") String token) {

        return service.listarTodos(token);
    }

    @PostMapping
    public ExamenClinico guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody ExamenClinicoRequest examen
    ) {

        return service.guardar(token, examen);
    }

    @PutMapping("/{id}")
    public ExamenClinico actualizar(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody ExamenClinicoRequest examen
    ) {

        return service.actualizar(
                token,
                id,
                examen);
    }

    @GetMapping("/{id}")
    public ExamenClinico buscarPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        return service.buscarPorId(token, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        service.eliminar(token, id);
    }
}