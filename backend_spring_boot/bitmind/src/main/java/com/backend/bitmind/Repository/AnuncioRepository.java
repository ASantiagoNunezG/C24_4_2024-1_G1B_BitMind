package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    List<Anuncio> findByTituloContaining(String fragmento);
}
