package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerCursoPorId(int idCurso) {
        return cursoRepository.findById(idCurso);
    }
    public List<Curso> obtenerCursosPorCiclo(Ciclo ciclo) {
        return cursoRepository.findByCiclo(ciclo);
    }

}
