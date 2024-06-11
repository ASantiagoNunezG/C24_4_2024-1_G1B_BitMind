package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findByCiclo(Ciclo ciclo);
}
