package datosmedicos_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import datosmedicos_service.model.SignosVitales;
import datosmedicos_service.service.SignosVitalesService;

@RestController
@RequestMapping("/signos-vitales")
@CrossOrigin("*")
public class SignosVitalesController {

    @Autowired
    private SignosVitalesService service;

    @GetMapping
    public List<SignosVitales> listarTodos() {

        return service.listarTodos();
    }

    @PostMapping
    public SignosVitales guardar(
            @RequestBody
            SignosVitales signosVitales
    ) {

        return service.guardar(
                signosVitales
        );
    }

    @GetMapping("/{id}")
    public SignosVitales buscarPorId(
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