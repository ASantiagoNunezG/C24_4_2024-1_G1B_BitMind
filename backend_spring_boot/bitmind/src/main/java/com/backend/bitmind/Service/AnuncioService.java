package com.backend.bitmind.Service;

import com.backend.bitmind.Dtos.AnuncioDTO;
import com.backend.bitmind.Model.Anuncio;

import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Repository.AnuncioRepository;
import com.backend.bitmind.Repository.UsuarioRepository;
import com.backend.bitmind.mapper.AnuncioMapper;
import com.backend.bitmind.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Anuncio> buscarAnunciosPorFragmentoDeNombre(String fragmento) {
        return anuncioRepository.findByTituloContaining(fragmento);
    }

    public List<AnuncioDTO> obtenerTodosLosAnuncios() {
        List<Anuncio> anuncios = anuncioRepository.findAllByOrderByFechaCreacionDesc();
        List<AnuncioDTO> anunciosDTO = new ArrayList<>();

        for (Anuncio anuncio : anuncios) {
            anunciosDTO.add(AnuncioMapper.toDTO(anuncio));
        }

        return anunciosDTO;
    }
    public Anuncio crearAnuncio(Anuncio anuncio, String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        anuncio.setUsuario(usuario);
        anuncio.setFechaCreacion(LocalDateTime.now());
        return anuncioRepository.save(anuncio);
    }
}
