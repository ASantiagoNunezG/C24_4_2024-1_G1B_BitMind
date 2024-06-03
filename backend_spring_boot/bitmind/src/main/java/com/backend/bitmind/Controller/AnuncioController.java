package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    @Autowired
    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    // Endpoint para obtener todos los anuncios
    @GetMapping
    public ResponseEntity<List<Anuncio>> obtenerTodosLosAnuncios() {
        List<Anuncio> anuncios = anuncioService.obtenerTodosLosAnuncios();
        return new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    // Endpoint para buscar anuncios por fragmento de nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Anuncio>> buscarAnunciosPorFragmentoDeNombre(@RequestParam String fragmento) {
        List<Anuncio> anuncios = anuncioService.buscarAnunciosPorFragmentoDeNombre(fragmento);
        return new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    // Otros endpoints para crear, actualizar, eliminar anuncios, etc.
}