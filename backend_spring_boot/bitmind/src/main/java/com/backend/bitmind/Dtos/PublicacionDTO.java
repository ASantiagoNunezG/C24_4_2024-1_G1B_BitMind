package com.backend.bitmind.Dtos;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacionDTO {
    private int idPublicacion;
    private String titulo;
    private String descripcion;
    private String imagen;
    private int vistas;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private UsuarioDTO usuario;
    private CursoDTO curso;
    private List<ValoracionDTO> valoraciones;
    private List<ArchivoDTO> archivos;
    private CicloDTO ciclo;
    private CarreraDTO carrera;
    private Double promedioValoracion;
}
