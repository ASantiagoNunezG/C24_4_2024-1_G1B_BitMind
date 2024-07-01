package com.backend.bitmind.Service;

import com.backend.bitmind.Dtos.PublicacionDTO;
import com.backend.bitmind.Model.*;
import com.backend.bitmind.Repository.PublicacionRepository;
import com.backend.bitmind.Repository.UsuarioRepository;
import com.backend.bitmind.mapper.PublicacionMapper;
import com.backend.bitmind.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAllByOrderByFechaCreacionDesc();
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();

        for (Publicacion publicacion : publicaciones) {
            publicacionesDTO.add(PublicacionMapper.toDTO(publicacion));
        }
        return publicacionesDTO;
    }

    public Publicacion obtenerPublicacionPorId(int id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public Publicacion guardarPublicacion(Publicacion publicacion, String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        publicacion.setUsuario(usuario);
        publicacion.setFechaCreacion(LocalDateTime.now());
        publicacion.setFechaModificacion(LocalDateTime.now());
        return publicacionRepository.save(publicacion);
    }

    public Publicacion actualizarPublicacion(Publicacion publicacion, int id, String correoUsuario) {
        Publicacion publicacionExistente = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        if (!publicacionExistente.getUsuario().getCorreo().equals(correoUsuario)) {
            throw new RuntimeException("No tienes permiso para actualizar esta publicación");
        }

        publicacionExistente.setTitulo(publicacion.getTitulo());
        publicacionExistente.setDescripcion(publicacion.getDescripcion());
        publicacionExistente.setImagen(publicacion.getImagen());
        publicacionExistente.setFechaModificacion(LocalDateTime.now());

        return publicacionRepository.save(publicacionExistente);
    }

    public void eliminarPublicacion(int id, String correoUsuario) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        if (!publicacion.getUsuario().getCorreo().equals(correoUsuario)) {
            throw new RuntimeException("No tienes permiso para eliminar esta publicación");
        }

        publicacionRepository.deleteById(id);
    }

    //Incrementar Vistas
    public void incrementarVistas(int idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion).orElse(null);
        if (publicacion != null) {
            publicacion.incrementarVistas();
            publicacionRepository.save(publicacion);
        }
    }

    public List<Publicacion> buscarPorTitulo(String titulo) {
        return publicacionRepository.findByTituloContaining(titulo);
    }

    public List<PublicacionDTO> obtenerPublicacionesPorCarrera(Carrera carrera) {
        List<Publicacion> publicaciones = publicacionRepository.findByCurso_Carrera(carrera);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesDTO.add(PublicacionMapper.toDTO(publicacion));
        }
        return publicacionesDTO;
    }

    public List<PublicacionDTO> obtenerPublicacionesPorCiclo(Ciclo ciclo) {
        List<Publicacion> publicaciones = publicacionRepository.findByCurso_Ciclo(ciclo);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesDTO.add(PublicacionMapper.toDTO(publicacion));
        }
        return publicacionesDTO;
    }

    public List<PublicacionDTO> obtenerPublicacionesPorCurso(Curso curso) {
        List<Publicacion> publicaciones = publicacionRepository.findByCurso(curso);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesDTO.add(PublicacionMapper.toDTO(publicacion));
        }
        return publicacionesDTO;
    }
}