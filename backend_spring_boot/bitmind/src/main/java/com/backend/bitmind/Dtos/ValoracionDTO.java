package com.backend.bitmind.Dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ValoracionDTO {
    private int idValoracion;
    private int escala;
    private LocalDateTime fechaCreacion;
    private UsuarioDTO usuario;
    private PublicacionDTO publicacion;
}
