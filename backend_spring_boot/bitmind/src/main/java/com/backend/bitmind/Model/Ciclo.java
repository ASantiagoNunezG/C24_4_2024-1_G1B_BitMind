package com.backend.bitmind.Model;

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

    @OneToMany(mappedBy = "ciclo")
    private List<Curso> cursos;
}