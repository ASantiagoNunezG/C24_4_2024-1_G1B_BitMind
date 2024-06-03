package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable int id) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(id);
        if (publicacion != null) {
            return new ResponseEntity<>(publicacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Publicacion> guardarPublicacion(@RequestBody Publicacion publicacion) {
        Publicacion nuevaPublicacion = publicacionService.guardarPublicacion(publicacion);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> actualizarPublicacion(@PathVariable int id, @RequestBody Publicacion publicacion) {
        Publicacion publicacionExistente = publicacionService.obtenerPublicacionPorId(id);
        if (publicacionExistente != null) {
            publicacion.setIdPublicacion(id);
            Publicacion publicacionActualizada = publicacionService.guardarPublicacion(publicacion);
            return new ResponseEntity<>(publicacionActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable int id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Publicacion>> buscarPublicacionesPorTitulo(@RequestParam String titulo) {
        List<Publicacion> publicaciones = publicacionService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }
}
