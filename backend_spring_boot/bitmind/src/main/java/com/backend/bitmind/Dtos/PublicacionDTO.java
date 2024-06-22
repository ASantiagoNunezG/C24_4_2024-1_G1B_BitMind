package com.backend.bitmind.Dtos;

import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.Model.Curso;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Model.Valoracion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacionDTO {

    private int idPublicacion;

    private String titulo;

    private String descripcion;

    private String imagen;

    private String vistas;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    private List<Valoracion> valoraciones;

    private List<Archivo> archivos;

    private Double promedioValoracion;
}
