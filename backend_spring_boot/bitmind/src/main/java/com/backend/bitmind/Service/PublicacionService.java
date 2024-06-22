package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    // Inyecta tu repositorio de publicaciones y otros servicios necesarios

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        for (Publicacion publicacion : publicaciones) {
            String nombreArchivo = publicacion.getImagen();
            String urlCompleta = "https://bucketbitmind.s3.amazonaws.com/" + nombreArchivo;
            publicacion.setImagen(urlCompleta);
        }
        return publicaciones;
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