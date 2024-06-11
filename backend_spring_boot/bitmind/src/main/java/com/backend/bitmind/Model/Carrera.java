package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carreras")
public class Carrera {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private int idCarrera;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "carrera")
    @JsonManagedReference
    private List<Ciclo> ciclos;
}
