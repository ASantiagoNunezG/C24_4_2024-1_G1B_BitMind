package com.backend.bitmind.servicios;

import com.backend.bitmind.modelos.Usuario;
import com.backend.bitmind.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorCorreo(String correo) {
        List<Usuario> users = usuarioRepository.findAll();
        for (Usuario user : users) {
            if (user.getCorreo().equals(correo)) {
                return user;
            }
        }
        return null;
    }


    //Méto para registrar un nuevo usuario
    public Usuario registrarseUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}