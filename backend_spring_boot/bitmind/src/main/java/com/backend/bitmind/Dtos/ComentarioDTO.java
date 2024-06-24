package com.backend.bitmind.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioDTO {
    private int idComentario;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private UsuarioDTO usuario;
}
