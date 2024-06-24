package com.backend.bitmind.Service;

import com.backend.bitmind.Dtos.PublicacionDTO;
import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Repository.PublicacionRepository;
import com.backend.bitmind.mapper.PublicacionMapper;
import com.backend.bitmind.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

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

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public void eliminarPublicacion(int id) {
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

    //Para los filtros
    public List<Publicacion> buscarPorTitulo(String titulo) {
        return publicacionRepository.findByTituloContaining(titulo);
    }
    public List<Publicacion> obtenerPublicacionesPorCarrera(Carrera carrera) {
        return publicacionRepository.findByCurso_Carrera(carrera);
    }

    public List<Publicacion> obtenerPublicacionesPorCiclo(Ciclo ciclo) {
        return publicacionRepository.findByCurso_Ciclo(ciclo);
    }

    public List<Publicacion> obtenerPublicacionesPorCurso(Curso curso) {
        return publicacionRepository.findByCurso(curso);
    }
}