package com.nunez.abraham.bitmind_frontend_movil.models

import java.time.LocalDateTime

data class Publicacion(
    val idPublicacion: Int,
    val titulo: String,
    val descripcion: String,
    val imagen: String,
    val vistas: String,
    val fechaCreacion: LocalDateTime,
    val fechaModificacion: LocalDateTime,
    val usuario: Usuario,
    val curso: Curso,
    val valoraciones: List<Valoracion>,
    val archivos: List<Archivo>
)
