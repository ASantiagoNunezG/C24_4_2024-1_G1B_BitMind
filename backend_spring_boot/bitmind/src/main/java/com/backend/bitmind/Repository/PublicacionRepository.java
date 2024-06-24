package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByTituloContaining(String titulo);
    // Filtrar publicaciones por ciclo
    List<Publicacion> findByCurso_Ciclo(Ciclo ciclo);

    // Filtrar publicaciones por carrera
    List<Publicacion> findByCurso_Carrera(Carrera carrera);

    //Filtrar publicacion por curso
    List<Publicacion> findByCurso(Curso curso);

    //Para mostrar el mas reciente
    List<Publicacion> findAllByOrderByFechaCreacionDesc();
}