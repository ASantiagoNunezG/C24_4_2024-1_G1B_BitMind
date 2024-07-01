package com.backend.bitmind.Controller.Public;

import com.backend.bitmind.Dtos.AnuncioDTO;
import com.backend.bitmind.Service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/anunciosM")
public class AnuncioMController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    public AnuncioMController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDTO>> obtenerTodosLosAnuncios() {
        List<AnuncioDTO> anunciosDTO = anuncioService.obtenerTodosLosAnuncios();
        return new ResponseEntity<>(anunciosDTO, HttpStatus.OK);
    }

}