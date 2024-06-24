package com.backend.bitmind.Dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    private int idUsuario;
    private String nombres;
    private String correo;
    private String imagen;
}
