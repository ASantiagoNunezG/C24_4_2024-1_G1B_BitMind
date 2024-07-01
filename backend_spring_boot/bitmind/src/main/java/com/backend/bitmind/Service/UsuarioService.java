package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Repository.UsuarioRepository;
import com.backend.bitmind.jwt.UsuarioDetails;
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
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Security
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String correo) throws  UsernameNotFoundException{
        Optional<Usuario> usuarioDetalle = usuarioRepository.findByCorreo(correo);
        //convirtiendo usuarioDetalle a UserDetails
        return usuarioDetalle.map(UsuarioDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"+correo));
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();

        usuarioRepository.findAll().forEach(users::add);

        return users;
    }

    public String nuevoUsuario(Usuario usuario){
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
