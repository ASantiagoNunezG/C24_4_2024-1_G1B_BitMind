package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAll();
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
}