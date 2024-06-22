package com.nunez.abraham.bitmind_frontend_movil.models

data class Ciclo(
    val idCiclo: Int,
    val nombre: String,
    val cursos: List<Curso>
)
