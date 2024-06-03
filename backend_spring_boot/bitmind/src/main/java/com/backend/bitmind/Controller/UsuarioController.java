package com.backend.bitmind.Controller;

import com.backend.bitmind.Model.UserInfo;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Service.JwtService;
import com.backend.bitmind.Service.UserInfoService;
import com.backend.bitmind.Service.UsuarioService;
import com.backend.bitmind.dtos.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class UsuarioController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String welcome() {
        return "Bienvenido a este endpoint no seguro";
    }
    @PostMapping("/nuevo")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }
    @GetMapping("/yo")
    @PreAuthorize("hasAuthority('USUARIO')")
    public String userProfile() {

        return "Bienvenido al perfil del usuario";
    }
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<String> getCurrentUser(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        return ResponseEntity.ok("El nombre de usuario es: " + username);
    }

    @PostMapping("/generarToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Peticion invalida !");
        }
    }
}
