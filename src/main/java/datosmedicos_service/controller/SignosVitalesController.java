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
    public List<SignosVitales> listarTodos(@RequestHeader("Authorization") String token) {

        return service.listarTodos(token);
    }

    @PostMapping
    public SignosVitales guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody
            SignosVitales signosVitales
    ) {

        return service.guardar(
                token,signosVitales
        );
    }

    @GetMapping("/{id}")
    public SignosVitales buscarPorId(
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