package com.backend.bitmind.Controller;

import com.backend.bitmind.Dtos.ArchivoDTO;
import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.Service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping
    public ResponseEntity<List<ArchivoDTO>> obtenerTodosLosArchivos() {
        List<ArchivoDTO> archivos = archivoService.obtenerTodosLosArchivos();
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoDTO> obtenerArchivoPorId(@PathVariable int id) {
        ArchivoDTO archivo = archivoService.obtenerArchivoPorId(id);
        if (archivo != null) {
            return new ResponseEntity<>(archivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("guardar")
    public ResponseEntity<ArchivoDTO> guardarArchivo(@RequestBody ArchivoDTO archivoDTO, Principal principal) {
        if (archivoDTO.getPublicacion() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String correoUsuario = principal.getName();
        ArchivoDTO nuevoArchivo = archivoService.guardarArchivo(archivoDTO, correoUsuario);
        return new ResponseEntity<>(nuevoArchivo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoDTO> actualizarArchivo(@PathVariable int id, @RequestBody ArchivoDTO archivoDTO, Principal principal) {
        if (archivoDTO.getPublicacion() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String correoUsuario = principal.getName();
        try {
            ArchivoDTO archivoActualizado = archivoService.actualizarArchivo(archivoDTO, id, correoUsuario);
            return new ResponseEntity<>(archivoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable int id, Principal principal) {
        String correoUsuario = principal.getName();
        try {
            archivoService.eliminarArchivo(id, correoUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}