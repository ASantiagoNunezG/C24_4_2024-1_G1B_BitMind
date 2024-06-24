package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.AnuncioDTO;
import com.backend.bitmind.Dtos.UsuarioDTO;
import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.util.UrlUtil;

public class AnuncioMapper {

    public static AnuncioDTO toDTO(Anuncio anuncio) {
        if (anuncio == null) {
            return null;
        }

        AnuncioDTO anuncioDTO = new AnuncioDTO();
        anuncioDTO.setIdAnuncio(anuncio.getIdAnuncio());
        anuncioDTO.setTitulo(anuncio.getTitulo());
        anuncioDTO.setDescripcion(anuncio.getDescripcion());
        anuncioDTO.setImagen(UrlUtil.construirUrl(anuncio.getImagen()));
        anuncioDTO.setFechaCreacion(anuncio.getFechaCreacion());

        Usuario usuario = anuncio.getUsuario();
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setNombres(usuario.getNombres());
            usuarioDTO.setCorreo(usuario.getCorreo());
            usuarioDTO.setImagen(UrlUtil.construirUrl(usuario.getImagen()));
            anuncioDTO.setUsuario(usuarioDTO);
        }

        return anuncioDTO;
    }
}
