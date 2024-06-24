package com.backend.bitmind.Controller;

import com.backend.bitmind.Dtos.ForoDTO;
import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Service.ForoService;
import com.backend.bitmind.mapper.ForoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/foros")
public class ForoController {
    @Autowired
    private ForoService foroService;

    //Endpoint para obtener todos los foros
    @GetMapping
    public ResponseEntity<List<ForoDTO>> obtenerTodosLosForos() {
        List<ForoDTO> forosDTO = foroService.obtenerTodosLosForos();
        return new ResponseEntity<>(forosDTO,HttpStatus.OK);
    }

    //endopint para crear un nuevo foro, pero con el id del usuario logeado.
    @PostMapping
    public ResponseEntity<Foro> crearForo(@RequestBody Foro foro) {
        Foro nuevoForo = foroService.guardarForo(foro);
        return new ResponseEntity<>(nuevoForo, HttpStatus.CREATED);
    }

    //endpoint para obtener un foro especifico
    @GetMapping("/{id}")
    public ResponseEntity<ForoDTO> obtenerForoPorId(@PathVariable int id) {
        Foro foro = foroService.obtenerForoPorId(id);
        if (foro != null) {
            ForoDTO foroDTO = ForoMapper.toDTO(foro);
            return new ResponseEntity<>(foroDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
