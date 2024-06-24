package com.backend.bitmind.jwt;

import com.backend.bitmind.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioMangerConfig implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException{
        return usuarioRepository
                .findByCorreo(correo)
                .map(UsuarioConfig::new)
                .orElseThrow(()->new UsernameNotFoundException("UsuarioCorreo:"+correo+"no existe"));
    }
}
