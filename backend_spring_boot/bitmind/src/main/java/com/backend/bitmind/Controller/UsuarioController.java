package com.backend.bitmind.Controller;

import com.backend.bitmind.Dtos.CarreraDTO;
import com.backend.bitmind.Dtos.CicloDTO;
import com.backend.bitmind.Dtos.CursoDTO;
import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Service.CarreraService;
import com.backend.bitmind.Service.CicloService;
import com.backend.bitmind.Service.CursoService;
import com.backend.bitmind.Service.UsuarioService;
import com.backend.bitmind.jwt.AuthRequest;
import com.backend.bitmind.jwt.JwtService;
import com.backend.bitmind.mapper.CarreraMapper;
import com.backend.bitmind.mapper.CicloMapper;
import com.backend.bitmind.mapper.CursoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("inicio")
    public String welcome() {
        return "Bienvenido a este endpoint no seguro";
    }

    @GetMapping("perfil")
    public ResponseEntity<Usuario> userProfile(Principal principal) {
        String correo = principal.getName();
        Optional<Usuario> usuario = usuarioService.findByCorreo(correo);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("nuevo")
    public String addNewUser(@RequestBody Usuario usuario) {
        return usuarioService.nuevoUsuario(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios = usuarioService.allUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("nuevoToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getClave()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getCorreo());
        } else {
            throw new UsernameNotFoundException("Peticion invalida!");
        }
    }


    @Autowired
    private CicloService cicloService;

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private CursoService cursoService;
    @GetMapping("carreras")
    public ResponseEntity<List<CarreraDTO>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodasLasCarreras();
        List<CarreraDTO> carrerasDTO = carreras.stream()
                .map(CarreraMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(carrerasDTO, HttpStatus.OK);
    }

    @GetMapping("ciclos")
    public ResponseEntity<List<CicloDTO>> obtenerCiclos() {
        List<Ciclo> ciclos = cicloService.obtenerTodosLosCiclos();
        List<CicloDTO> ciclosDTO = ciclos.stream()
                .map(CicloMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ciclosDTO, HttpStatus.OK);
    }

    @GetMapping("cursos")
    public ResponseEntity<List<CursoDTO>> obtenerCursos() {
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        List<CursoDTO> cursosDTO = cursos.stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cursosDTO, HttpStatus.OK);
    }
}
