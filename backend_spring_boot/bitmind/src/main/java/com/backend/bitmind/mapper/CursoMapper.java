package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.CarreraDTO;
import com.backend.bitmind.Dtos.CicloDTO;
import com.backend.bitmind.Dtos.CursoDTO;
import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Model.Curso;

public class CursoMapper {
    public static CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdCurso(curso.getIdCurso());
        cursoDTO.setNombre(curso.getNombre());

        Ciclo ciclo = curso.getCiclo();
        if (ciclo != null) {
            CicloDTO cicloDTO = new CicloDTO();
            cicloDTO.setIdCiclo(ciclo.getIdCiclo());
            cicloDTO.setNombre(ciclo.getNombre());
            cursoDTO.setCiclo(cicloDTO);
        }

        Carrera carrera = curso.getCarrera();
        if (carrera != null) {
            CarreraDTO carreraDTO = new CarreraDTO();
            carreraDTO.setIdCarrera(carrera.getIdCarrera());
            carreraDTO.setNombre(carrera.getNombre());
            cursoDTO.setCarrera(carreraDTO);
        }

        return cursoDTO;
    }
}
