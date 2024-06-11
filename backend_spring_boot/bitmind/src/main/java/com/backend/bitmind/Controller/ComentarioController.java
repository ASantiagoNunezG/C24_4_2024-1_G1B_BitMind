package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Comentario;
import com.backend.bitmind.Service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    //endpoint para crear un nuevo comentario con el id del usuario logeado(falta implementar)
    @PostMapping
    public Comentario crearComentario(@RequestBody Comentario comentario) {
        return comentarioService.guardarComentario(comentario);
    }

}