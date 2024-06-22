package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cursos")
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private int idCurso;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_ciclo", referencedColumnName = "id_ciclo")
    @JsonIgnore
    private Ciclo ciclo;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    @JsonIgnore
    private Carrera carrera;
}
