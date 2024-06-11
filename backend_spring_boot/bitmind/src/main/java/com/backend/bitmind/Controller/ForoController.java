package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Service.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/foros")
public class ForoController {
    @Autowired
    private ForoService foroService;

    @GetMapping
    public List<Foro> obtenerTodosLosForos() {
        return foroService.obtenerTodosLosForos();
    }

    //endopint para crear un nuevo foro, pero con el id del usuario logeado.
    @PostMapping
    public Foro crearForo(@RequestBody Foro foro) {
        return foroService.guardarForo(foro);
    }

}
