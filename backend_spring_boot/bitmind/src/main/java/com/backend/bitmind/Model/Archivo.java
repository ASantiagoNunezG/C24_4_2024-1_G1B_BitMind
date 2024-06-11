package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "archivos")
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private int idArchivo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "url")
    private String url;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion")
    @JsonBackReference
    private Publicacion publicacion;
}
