package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "clave")
    private String clave;

    @Column( name = "imagen")
    private String imagen;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Foro> foros;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Anuncio> anuncios;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicaciones;
}