package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Service.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foros")
public class ForoController {
    @Autowired
    private ForoService foroService;

    @GetMapping
    public List<Foro> obtenerTodosLosForos() {
        return foroService.obtenerTodosLosForos();
    }

    @PostMapping
    public Foro crearForo(@RequestBody Foro foro) {
        return foroService.guardarForo(foro);
    }

    // Otros métodos de controlador según sea necesario
}
