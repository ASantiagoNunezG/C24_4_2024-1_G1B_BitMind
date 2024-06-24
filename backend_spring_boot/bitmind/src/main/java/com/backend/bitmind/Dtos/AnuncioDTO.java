package com.backend.bitmind.Dtos;

import jakarta.persistence.Lob;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnuncioDTO {
    private int idAnuncio;
    private String titulo;

    @Lob
    private String descripcion;

    private String imagen;
    private LocalDateTime fechaCreacion;

    private UsuarioDTO usuario;
}
