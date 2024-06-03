package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Comentario;
import com.backend.bitmind.Service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    //no creo que sirve , tal vez para leer
    @GetMapping
    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioService.obtenerTodosLosComentarios();
    }

    @PostMapping
    public Comentario crearComentario(@RequestBody Comentario comentario) {
        return comentarioService.guardarComentario(comentario);
    }

    // Otros métodos de controlador según sea necesario
}