package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private int idPublicacion;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "vistas")
    private int vistas = 0; // Iniciar por defecto en 0

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private Curso curso;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Valoracion> valoraciones;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Archivo> archivos;
}
