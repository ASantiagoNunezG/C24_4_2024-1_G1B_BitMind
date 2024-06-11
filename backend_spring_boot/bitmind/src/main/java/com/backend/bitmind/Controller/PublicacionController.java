package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.*;
import com.backend.bitmind.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    //Enpoint para obtener una publicacion por id//para detalles y aqui tambien se muestran sus archivos relacionados
    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable int id) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(id);
        if (publicacion != null) {
            return new ResponseEntity<>(publicacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint para guardar una nueva publicacion, crear
    @PostMapping
    public ResponseEntity<Publicacion> guardarPublicacion(@RequestBody Publicacion publicacion) {
        Publicacion nuevaPublicacion = publicacionService.guardarPublicacion(publicacion);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }

    //Endpint para actualizar una publicacion
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
    //endpoint para eliminar una publicacion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable int id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //Endpoint para obtener publicaciones por titulo
    @GetMapping("/buscar")
    public ResponseEntity<List<Publicacion>> buscarPublicacionesPorTitulo(@RequestParam String titulo) {
        List<Publicacion> publicaciones = publicacionService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    // Endpoint para obtener publicaciones por carrera
    @GetMapping("/por-carrera/{idCarrera}")
    public List<Publicacion> obtenerPublicacionesPorCarrera(@PathVariable int idCarrera) {
        Carrera carrera = carreraService.obtenerCarreraPorId(idCarrera)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        return publicacionService.obtenerPublicacionesPorCarrera(carrera);
    }

    // Endpoint para obtener publicaciones por ciclo
    @GetMapping("/por-ciclo/{idCiclo}")
    public List<Publicacion> obtenerPublicacionesPorCiclo(@PathVariable int idCiclo) {
        Ciclo ciclo = cicloService.obtenerCicloPorId(idCiclo)
                .orElseThrow(() -> new RuntimeException("Ciclo no encontrado"));
        return publicacionService.obtenerPublicacionesPorCiclo(ciclo);
    }

    // Endpoint para obtener publicaciones por curso
    @GetMapping("/por-curso/{idCurso}")
    public List<Publicacion> obtenerPublicacionesPorCurso(@PathVariable int idCurso) {
        Curso curso = cursoService.obtenerCursoPorId(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        return publicacionService.obtenerPublicacionesPorCurso(curso);
    }


    // Endpoint para obtener los ciclos por carrera
    @GetMapping("/ciclos-por-carrera/{idCarrera}")
    public List<Ciclo> obtenerCiclosPorCarrera(@PathVariable int idCarrera) {
        Carrera carrera = carreraService.obtenerCarreraPorId(idCarrera)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        return cicloService.obtenerCiclosPorCarrera(carrera);
    }

    // Endpoint para obtener los cursos por ciclo
    @GetMapping("/cursos-por-ciclo/{idCiclo}")
    public List<Curso> obtenerCursosPorCiclo(@PathVariable int idCiclo) {
        Ciclo ciclo = cicloService.obtenerCicloPorId(idCiclo)
                .orElseThrow(() -> new RuntimeException("Ciclo no encontrado"));
        return cursoService.obtenerCursosPorCiclo(ciclo);
    }


    @GetMapping("/carreras")
    public ResponseEntity<List<Carrera>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodasLasCarreras();
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }
    /*
    // Endpoint para guardar una nueva publicación
    @PostMapping
    public ResponseEntity<Publicacion> guardarPublicacion(@RequestHeader("Authorization") String token, @RequestBody Publicacion publicacion) {
        // Extraer el nombre de usuario del token
        String username = jwtService.extractUsername(token.substring(7)); // Suponiendo que el token comienza con "Bearer "

        // Obtener el usuario por su nombre de usuario
        UserInfoService usuario = (UserInfoService) userInfoService.loadUserByUsername(username);
        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.BAD_REQUEST);
        }

        // Asignar el usuario a la publicación
        publicacion.setUsuario(usuario);

        // Guardar la publicación
        Publicacion nuevaPublicacion = publicacionService.guardarPublicacion(publicacion);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }*/
}
