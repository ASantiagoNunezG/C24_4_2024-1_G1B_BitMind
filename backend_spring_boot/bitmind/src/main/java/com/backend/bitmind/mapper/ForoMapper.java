package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.ComentarioDTO;
import com.backend.bitmind.Dtos.ForoDTO;
import com.backend.bitmind.Dtos.UsuarioDTO;
import com.backend.bitmind.Model.Comentario;
import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

public class ForoMapper {
    public static ForoDTO toDTO(Foro foro) {
        if (foro == null) {
            return null;
        }

        ForoDTO foroDTO = new ForoDTO();
        foroDTO.setIdForo(foro.getIdForo());
        foroDTO.setTitulo(foro.getTitulo());
        foroDTO.setContenido(foro.getContenido());
        foroDTO.setFechaCreacion(foro.getFechaCreacion());

        Usuario usuario = foro.getUsuario();
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setNombres(usuario.getNombres());
            usuarioDTO.setCorreo(usuario.getCorreo());
            usuarioDTO.setImagen(UrlUtil.construirUrl(usuario.getImagen()));
            foroDTO.setUsuario(usuarioDTO);
        }

        List<ComentarioDTO> comentariosDTO = new ArrayList<>();
        for (Comentario comentario : foro.getComentarios()) {
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            comentarioDTO.setIdComentario(comentario.getIdComentario());
            comentarioDTO.setContenido(comentario.getContenido());
            comentarioDTO.setFechaCreacion(comentario.getFechaCreacion());

            Usuario comentarioUsuario = comentario.getUsuario();
            if (comentarioUsuario != null) {
                UsuarioDTO comentarioUsuarioDTO = new UsuarioDTO();
                comentarioUsuarioDTO.setIdUsuario(comentarioUsuario.getIdUsuario());
                comentarioUsuarioDTO.setNombres(comentarioUsuario.getNombres());
                comentarioUsuarioDTO.setCorreo(comentarioUsuario.getCorreo());
                comentarioUsuarioDTO.setImagen(UrlUtil.construirUrl(comentarioUsuario.getImagen()));
                comentarioDTO.setUsuario(comentarioUsuarioDTO);
            }

            comentariosDTO.add(comentarioDTO);
        }
        foroDTO.setComentarios(comentariosDTO);

        return foroDTO;
    }
}
