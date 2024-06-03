package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Anuncio;
import com.backend.bitmind.Repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    public List<Anuncio> buscarAnunciosPorFragmentoDeNombre(String fragmento) {
        return anuncioRepository.findByTituloContaining(fragmento);
    }

    public List<Anuncio> obtenerTodosLosAnuncios() {
        return anuncioRepository.findAll();
    }
}
