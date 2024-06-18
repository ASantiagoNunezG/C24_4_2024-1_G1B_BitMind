package com.nunez.abraham.bitmind_frontend_movil.models

data class Carrera(
    val idCarrera: Int,
    val nombre: String,
    val ciclos: List<Ciclo>
)
