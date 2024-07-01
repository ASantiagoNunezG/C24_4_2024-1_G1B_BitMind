package com.backend.bitmind.Dtos;

import lombok.Data;

@Data
public class ArchivoDTO {
    private int idArchivo;
    private String nombre;
    private String url;
    private String tipo;
    private PublicacionDTO publicacion;
}
