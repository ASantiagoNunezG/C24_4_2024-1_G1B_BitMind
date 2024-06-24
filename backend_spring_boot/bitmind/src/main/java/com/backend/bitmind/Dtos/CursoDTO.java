package com.backend.bitmind.Dtos;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import lombok.Data;

@Data
public class CursoDTO {
    private int idCurso;
    private String nombre;
    private CicloDTO ciclo;
    private CarreraDTO carrera;
}
