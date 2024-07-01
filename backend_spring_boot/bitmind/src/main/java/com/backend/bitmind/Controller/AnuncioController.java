package com.backend.bitmind.Controller;

import com.backend.bitmind.Dtos.AnuncioDTO;
import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    // Endpoint para obtener todos los anuncios
    @GetMapping
    public ResponseEntity<List<AnuncioDTO>> obtenerTodosLosAnuncios() {
        List<AnuncioDTO> anunciosDTO = anuncioService.obtenerTodosLosAnuncios();
        return new ResponseEntity<>(anunciosDTO,HttpStatus.OK);
    }

    // Endpoint para buscar anuncios por fragmento de nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Anuncio>> buscarAnunciosPorFragmentoDeNombre(@RequestParam String fragmento) {
        List<Anuncio> anuncios = anuncioService.buscarAnunciosPorFragmentoDeNombre(fragmento);
        return new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    // Otros endpoints para crear, actualizar, eliminar anuncios, etc.
    @PostMapping("/crear")
    public ResponseEntity<Anuncio> crearAnuncio(@RequestBody Anuncio anuncio, Principal principal) {
        String correoUsuario = principal.getName();
        Anuncio nuevoAnuncio = anuncioService.crearAnuncio(anuncio, correoUsuario);
        return new ResponseEntity<>(nuevoAnuncio, HttpStatus.CREATED);
    }
}