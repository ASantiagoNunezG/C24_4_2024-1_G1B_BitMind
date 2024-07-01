package com.backend.bitmind.Controller;

import com.backend.bitmind.Dtos.CarreraDTO;
import com.backend.bitmind.Dtos.CicloDTO;
import com.backend.bitmind.Dtos.CursoDTO;
import com.backend.bitmind.Dtos.PublicacionDTO;
import com.backend.bitmind.Model.*;
import com.backend.bitmind.Service.*;
import com.backend.bitmind.mapper.CarreraMapper;
import com.backend.bitmind.mapper.CicloMapper;
import com.backend.bitmind.mapper.CursoMapper;
import com.backend.bitmind.mapper.PublicacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

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


    //Endpoint para guardar publicacion
    @PostMapping("/guardar")
    public ResponseEntity<Publicacion> guardarPublicacion(@RequestBody Publicacion publicacion, Principal principal) {
        String correoUsuario = principal.getName();
        Publicacion nuevaPublicacion = publicacionService.guardarPublicacion(publicacion, correoUsuario);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> actualizarPublicacion(@PathVariable int id, @RequestBody Publicacion publicacion, Principal principal) {
        String correoUsuario = principal.getName();
        try {
            Publicacion publicacionActualizada = publicacionService.actualizarPublicacion(publicacion, id, correoUsuario);
            return new ResponseEntity<>(publicacionActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable int id, Principal principal) {
        String correoUsuario = principal.getName();
        try {
            publicacionService.eliminarPublicacion(id, correoUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id}/addview")
    public ResponseEntity<?> incrementarVistas(@PathVariable int id) {
        publicacionService.incrementarVistas(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PublicacionDTO>> buscarPublicacionesPorTitulo(@RequestParam String titulo) {
        List<Publicacion> publicaciones = publicacionService.buscarPorTitulo(titulo);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesDTO.add(PublicacionMapper.toDTO(publicacion));
        }
        if (!publicacionesDTO.isEmpty()) {
            return new ResponseEntity<>(publicacionesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/por-carrera/{idCarrera}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesPorCarrera(@PathVariable int idCarrera) {
        Carrera carrera = carreraService.obtenerCarreraPorId(idCarrera)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesPorCarrera(carrera);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/por-ciclo/{idCiclo}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesPorCiclo(@PathVariable int idCiclo) {
        Ciclo ciclo = cicloService.obtenerCicloPorId(idCiclo)
                .orElseThrow(() -> new RuntimeException("Ciclo no encontrado"));
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesPorCiclo(ciclo);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/por-curso/{idCurso}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesPorCurso(@PathVariable int idCurso) {
        Curso curso = cursoService.obtenerCursoPorId(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesPorCurso(curso);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/cursos-por-ciclo/{idCiclo}")
    public ResponseEntity<List<CursoDTO>> obtenerCursosPorCiclo(@PathVariable int idCiclo) {
        Ciclo ciclo = cicloService.obtenerCicloPorId(idCiclo)
                .orElseThrow(() -> new RuntimeException("Ciclo no encontrado"));
        List<Curso> cursos = cursoService.obtenerCursosPorCiclo(ciclo);
        List<CursoDTO> cursosDTO = cursos.stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cursosDTO, HttpStatus.OK);
    }


}
