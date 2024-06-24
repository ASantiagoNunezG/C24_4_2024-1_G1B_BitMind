package com.backend.bitmind.Dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ForoDTO {
    private int idForo;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private UsuarioDTO usuario;
    private List<ComentarioDTO> comentarios;
}
