package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findByCiclo(Ciclo ciclo);
}
