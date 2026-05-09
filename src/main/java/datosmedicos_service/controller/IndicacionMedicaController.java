package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.IndicacionMedica;
import datosmedicos_service.service.IndicacionMedicaService;

@RestController
@RequestMapping("/indicaciones")
@CrossOrigin("*")
public class IndicacionMedicaController {

    @Autowired
    private IndicacionMedicaService service;

    @GetMapping
    public List<IndicacionMedica> listarTodos(@RequestHeader("Authorization") String token) {

        return service.listarTodos(token);
    }

    @PostMapping
    public IndicacionMedica guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody
            IndicacionMedica indicacion
    ) {

        return service.guardar(token,indicacion);
    }

    @GetMapping("/{id}")
    public IndicacionMedica buscarPorId(
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