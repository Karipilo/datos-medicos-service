package datosmedicos_service.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.Antropometria;
import datosmedicos_service.service.AntropometriaService;

@RestController
@RequestMapping("/antropometrias")
@CrossOrigin("*")
public class AntropometriaController {

    private final AntropometriaService service;

    public AntropometriaController(
            AntropometriaService service) {

        this.service = service;
    }

    @PostMapping(
            value = "/{fichaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Antropometria guardar(
            @PathVariable Long fichaId,
            @RequestBody Antropometria antropometria) {

        return service.guardar(
                fichaId,
                antropometria);
    }

    @GetMapping("/{fichaId}")
    public List<Antropometria> listar(
            @PathVariable Long fichaId) {

        return service.listarPorFicha(
                fichaId);
    }
}