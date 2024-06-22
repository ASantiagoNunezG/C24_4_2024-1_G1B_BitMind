package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;

    public List<Anuncio> buscarAnunciosPorFragmentoDeNombre(String fragmento) {
        return anuncioRepository.findByTituloContaining(fragmento);
    }

    public List<Anuncio> obtenerTodosLosAnuncios() {
        List<Anuncio> anuncios = anuncioRepository.findAll();
            for (Anuncio anuncio : anuncios) {
            String nombreArchivo = anuncio.getImagen();
            String urlCompleta = "https://bitmindfiles.s3.amazonaws.com/" + nombreArchivo;
            anuncio.setImagen(urlCompleta);
        }
        return anuncios;
    }
}
