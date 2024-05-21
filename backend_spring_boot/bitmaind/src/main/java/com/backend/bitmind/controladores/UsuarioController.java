package com.backend.bitmind.controladores;

import com.backend.bitmind.modelos.Usuario;
import com.backend.bitmind.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logearse")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.buscarPorCorreo(usuario.getCorreo());
        if (usuarioEncontrado != null && usuarioEncontrado.getClave().equals(usuario.getClave())) {
            return ResponseEntity.ok("Acceso concedido");
        } else {
            return ResponseEntity.status(401).body("Correo o clave incorrectos");
        }
    }

    @PostMapping("/registrarse")
    public Usuario registrar(@RequestBody Usuario usuario){
        return usuarioService.registrarseUsuario(usuario);
    }

}
