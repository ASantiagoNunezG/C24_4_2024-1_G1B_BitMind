package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.*;
import com.backend.bitmind.Model.*;
import com.backend.bitmind.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

public class PublicacionMapper {
    public static PublicacionDTO toDTO(Publicacion publicacion) {
        if (publicacion == null) {
            return null;
        }

        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setIdPublicacion(publicacion.getIdPublicacion());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setImagen(UrlUtil.construirUrl(publicacion.getImagen()));
        publicacionDTO.setVistas(publicacion.getVistas());
        publicacionDTO.setFechaCreacion(publicacion.getFechaCreacion());
        publicacionDTO.setFechaModificacion(publicacion.getFechaModificacion());

        Usuario usuario = publicacion.getUsuario();
        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setNombres(usuario.getNombres());
            usuarioDTO.setCorreo(usuario.getCorreo());
            usuarioDTO.setImagen(UrlUtil.construirUrl(usuario.getImagen()));
            publicacionDTO.setUsuario(usuarioDTO);
        }

        Curso curso = publicacion.getCurso();
        if (curso != null) {
            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setIdCurso(curso.getIdCurso());
            cursoDTO.setNombre(curso.getNombre());

            Ciclo ciclo = curso.getCiclo();
            if (ciclo != null) {
                CicloDTO cicloDTO = new CicloDTO();
                cicloDTO.setIdCiclo(ciclo.getIdCiclo());
                cicloDTO.setNombre(ciclo.getNombre());
                cursoDTO.setCiclo(cicloDTO);
                publicacionDTO.setCiclo(cicloDTO);
            }

            Carrera carrera = curso.getCarrera();
            if (carrera != null) {
                CarreraDTO carreraDTO = new CarreraDTO();
                carreraDTO.setIdCarrera(carrera.getIdCarrera());
                carreraDTO.setNombre(carrera.getNombre());
                cursoDTO.setCarrera(carreraDTO);
                publicacionDTO.setCarrera(carreraDTO);
            }
            publicacionDTO.setCurso(cursoDTO);
        }

        List<ValoracionDTO> valoracionesDTO = new ArrayList<>();
        for (Valoracion valoracion : publicacion.getValoraciones()) {
            ValoracionDTO valoracionDTO = new ValoracionDTO();
            valoracionDTO.setIdValoracion(valoracion.getIdValoracion());
            valoracionDTO.setEscala(valoracion.getEscala());
            valoracionDTO.setFechaCreacion(valoracion.getFechaCreacion());

            Usuario valoracionUsuario = valoracion.getUsuario();
            if (valoracionUsuario != null) {
                UsuarioDTO valoracionUsuarioDTO = new UsuarioDTO();
                valoracionUsuarioDTO.setIdUsuario(valoracionUsuario.getIdUsuario());
                valoracionUsuarioDTO.setNombres(valoracionUsuario.getNombres());
                valoracionUsuarioDTO.setCorreo(valoracionUsuario.getCorreo());
                valoracionUsuarioDTO.setImagen(UrlUtil.construirUrl(valoracionUsuario.getImagen()));
                valoracionDTO.setUsuario(valoracionUsuarioDTO);
            }

            valoracionesDTO.add(valoracionDTO);
        }
        publicacionDTO.setValoraciones(valoracionesDTO);

        List<ArchivoDTO> archivosDTO = new ArrayList<>();
        for (Archivo archivo : publicacion.getArchivos()) {
            ArchivoDTO archivoDTO = new ArchivoDTO();
            archivoDTO.setIdArchivo(archivo.getIdArchivo());
            archivoDTO.setNombre(archivo.getNombre());
            archivoDTO.setUrl(UrlUtil.construirUrl(archivo.getUrl()));
            archivoDTO.setTipo(archivo.getTipo());
            archivosDTO.add(archivoDTO);
        }
        publicacionDTO.setArchivos(archivosDTO);

        Double promedioValoracion = calcularPromedioValoracion(publicacion.getValoraciones());
        publicacionDTO.setPromedioValoracion(promedioValoracion);

        return publicacionDTO;
    }

    private static Double calcularPromedioValoracion(List<Valoracion> valoraciones) {
        if (valoraciones == null || valoraciones.isEmpty()) {
            return null;
        }

        double sum = 0.0;
        for (Valoracion valoracion : valoraciones) {
            sum += valoracion.getEscala();
        }
        return sum / valoraciones.size();
    }
}
