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

    @PostMapping("/{fichaId}")
    public SignosVitales guardar(
            @PathVariable Long fichaId,
            @RequestBody SignosVitales signosVitales) {

        return service.guardar(
                fichaId,
                signosVitales);
    }

    @GetMapping("/ficha/{fichaId}")
    public List<SignosVitales> listarPorFicha(
            @PathVariable Long fichaId) {

        return service.listarPorFicha(
                fichaId);
    }

    @GetMapping("/{id}")
    public SignosVitales buscarPorId(
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