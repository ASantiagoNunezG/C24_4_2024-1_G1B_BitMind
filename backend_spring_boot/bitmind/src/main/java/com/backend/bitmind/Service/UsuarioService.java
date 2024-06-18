package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();

        usuarioRepository.findAll().forEach(users::add);

        return users;
    }

}
