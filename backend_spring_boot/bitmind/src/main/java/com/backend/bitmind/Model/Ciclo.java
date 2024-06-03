package com.backend.bitmind.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ciclos")
public class Ciclo {
    @Id
    @Column(name = "id_ciclo")
    private Long idCiclo;
    @Column(name = "nombre")
    private String nombre;
    // Otros atributos y relaciones
    //GETTERS Y SETTERS

    public Long getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Long idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}