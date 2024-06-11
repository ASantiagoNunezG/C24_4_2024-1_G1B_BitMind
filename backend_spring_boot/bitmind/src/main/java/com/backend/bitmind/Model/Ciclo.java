package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "ciclos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciclo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_ciclo")
    private int idCiclo;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    @JsonBackReference
    private Carrera carrera;

    @OneToMany(mappedBy = "ciclo")
    private List<Curso> cursos;
}