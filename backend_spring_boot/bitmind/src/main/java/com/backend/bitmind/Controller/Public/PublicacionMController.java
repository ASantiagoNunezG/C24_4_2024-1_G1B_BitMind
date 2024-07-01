package com.backend.bitmind.Controller.Public;

import com.backend.bitmind.Dtos.PublicacionDTO;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Service.*;
import com.backend.bitmind.mapper.PublicacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/publicacionesM")
public class PublicacionMController {

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private CicloService cicloService;

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ValoracionService valoracionService;

    @Autowired
    private ArchivoService archivoService;


    //Endpoint para obtener todas las publicaciones
    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> obtenerTodasLasPublicaciones() {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    //Enpoint para obtener una publicacion por id//para detalles y aqui tambien se muestran sus archivos relacionados
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable int id) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(id);
        if (publicacion != null) {
            PublicacionDTO publicacionDTO = PublicacionMapper.toDTO(publicacion);
            return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint para las vistas
    @PostMapping("/{id}/addview")
    public ResponseEntity<?> incrementarVistas(@PathVariable int id) {
        publicacionService.incrementarVistas(id);
        return ResponseEntity.ok().build();
    }

}