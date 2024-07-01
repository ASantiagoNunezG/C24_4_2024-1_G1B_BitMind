package com.backend.bitmind.Controller.Public;

import com.backend.bitmind.Dtos.ForoDTO;
import com.backend.bitmind.Service.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/forosM")
public class ForoMController {
    @Autowired
    private ForoService foroService;

    //Endpoint para obtener todos los foros
    @GetMapping
    public ResponseEntity<List<ForoDTO>> obtenerTodosLosForos() {
        List<ForoDTO> forosDTO = foroService.obtenerTodosLosForos();
        return new ResponseEntity<>(forosDTO, HttpStatus.OK);
    }
}