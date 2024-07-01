package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Service.UsuarioService;
import com.backend.bitmind.jwt.AuthRequest;
import com.backend.bitmind.jwt.JwtService;
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
}
