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
    public List<IndicacionMedica> listarTodos() {

        return service.listarTodos();
    }

    @PostMapping
    public IndicacionMedica guardar(
            @RequestBody
            IndicacionMedica indicacion
    ) {

        return service.guardar(indicacion);
    }

    @GetMapping("/{id}")
    public IndicacionMedica buscarPorId(
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