package com.backend.bitmind.Dtos;

import lombok.Data;

@Data
public class CursoDTO {
    private int idCurso;
    private String nombre;
    private CicloDTO ciclo;
    private CarreraDTO carrera;
}
