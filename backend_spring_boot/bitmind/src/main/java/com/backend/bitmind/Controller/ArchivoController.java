package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.Service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping
    public ResponseEntity<List<Archivo>> obtenerTodosLosArchivos() {
        List<Archivo> archivos = archivoService.obtenerTodosLosArchivos();
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivo> obtenerArchivoPorId(@PathVariable int id) {
        Archivo archivo = archivoService.obtenerArchivoPorId(id);
        if (archivo != null) {
            return new ResponseEntity<>(archivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Archivo> guardarArchivo(@RequestBody Archivo archivo) {
        Archivo nuevoArchivo = archivoService.guardarArchivo(archivo);
        return new ResponseEntity<>(nuevoArchivo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Archivo> actualizarArchivo(@PathVariable int id, @RequestBody Archivo archivo) {
        Archivo archivoExistente = archivoService.obtenerArchivoPorId(id);
        if (archivoExistente != null) {
            archivo.setIdArchivo(id);
            Archivo archivoActualizado = archivoService.guardarArchivo(archivo);
            return new ResponseEntity<>(archivoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable int id) {
        archivoService.eliminarArchivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}